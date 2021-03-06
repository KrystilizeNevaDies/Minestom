package net.minestom.codegen.sound;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.squareup.javapoet.*;
import net.minestom.codegen.MinestomCodeGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.io.*;
import java.util.Collections;

public final class SoundEventGenerator extends MinestomCodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoundEventGenerator.class);
    private final InputStream soundsFile;
    private final File outputFolder;

    public SoundEventGenerator(@Nullable InputStream itemsFile, @NotNull File outputFolder) {
        this.soundsFile = itemsFile;
        this.outputFolder = outputFolder;
    }

    @Override
    public void generate() {
        if (soundsFile == null) {
            LOGGER.error("Failed to find sounds.json.");
            LOGGER.error("Stopped code generation for sounds.");
            return;
        }
        if (!outputFolder.exists() && !outputFolder.mkdirs()) {
            LOGGER.error("Output folder for code generation does not exist and could not be created.");
            return;
        }
        // Important classes we use alot
        ClassName namespaceIDClassName = ClassName.get("net.minestom.server.utils", "NamespaceID");
        ClassName registriesClassName = ClassName.get("net.minestom.server.registry", "Registries");

        JsonArray sounds = GSON.fromJson(new JsonReader(new InputStreamReader(soundsFile)), JsonArray.class);
        ClassName soundClassName = ClassName.get("net.minestom.server.sound", "SoundEvent");
        // Sound
        TypeSpec.Builder soundClass = TypeSpec.enumBuilder(soundClassName)
                .addSuperinterface(ClassName.get("net.kyori.adventure.key", "Keyed"))
                .addSuperinterface(ClassName.get("net.kyori.adventure.sound", "Sound", "Type"))
                .addModifiers(Modifier.PUBLIC).addJavadoc("AUTOGENERATED by " + getClass().getSimpleName());

        soundClass.addField(
                FieldSpec.builder(namespaceIDClassName, "id")
                        .addModifiers(Modifier.PRIVATE, Modifier.FINAL).addAnnotation(NotNull.class).build()
        );
        // static field
        soundClass.addField(
                FieldSpec.builder(ArrayTypeName.of(soundClassName), "VALUES")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("values()")
                        .build()
        );

        soundClass.addMethod(
                MethodSpec.constructorBuilder()
                        .addParameter(ParameterSpec.builder(namespaceIDClassName, "id").addAnnotation(NotNull.class).build())
                        .addStatement("this.id = id")
                        .addStatement("$T.soundEvents.put(id, this)", registriesClassName)
                        .build()
        );
        // Override key method (adventure)
        soundClass.addMethod(
                MethodSpec.methodBuilder("key")
                        .returns(ClassName.get("net.kyori.adventure.key", "Key"))
                        .addAnnotation(Override.class)
                        .addAnnotation(NotNull.class)
                        .addStatement("return this.id")
                        .addModifiers(Modifier.PUBLIC)
                        .build()
        );
        // getId method
        soundClass.addMethod(
                MethodSpec.methodBuilder("getId")
                        .returns(TypeName.SHORT)
                        .addStatement("return (short) ordinal()")
                        .addModifiers(Modifier.PUBLIC)
                        .build()
        );
        // getNamespaceID method
        soundClass.addMethod(
                MethodSpec.methodBuilder("getNamespaceID")
                        .returns(namespaceIDClassName)
                        .addAnnotation(NotNull.class)
                        .addStatement("return this.id")
                        .addModifiers(Modifier.PUBLIC)
                        .build()
        );
        // fromId Method
        soundClass.addMethod(
                MethodSpec.methodBuilder("fromId")
                        .returns(soundClassName)
                        .addAnnotation(Nullable.class)
                        .addParameter(TypeName.SHORT, "id")
                        .beginControlFlow("if(id >= 0 && id < VALUES.length)")
                        .addStatement("return VALUES[id]")
                        .endControlFlow()
                        .addStatement("return null")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .build()
        );
        // toString method
        soundClass.addMethod(
                MethodSpec.methodBuilder("toString")
                        .addAnnotation(NotNull.class)
                        .addAnnotation(Override.class)
                        .returns(String.class)
                        // this resolves to [Namespace]
                        .addStatement("return \"[\" + this.id + \"]\"")
                        .addModifiers(Modifier.PUBLIC)
                        .build()
        );

        // Use data
        for (JsonElement s : sounds) {
            JsonObject sound = s.getAsJsonObject();

            String soundName = sound.get("name").getAsString();
            soundClass.addEnumConstant(soundName, TypeSpec.anonymousClassBuilder(
                    "$T.from($S)",
                    namespaceIDClassName,
                    sound.get("id").getAsString()
                    ).build()
            );
        }

        // Write files to outputFolder
        writeFiles(
                Collections.singletonList(
                        JavaFile.builder("net.minestom.server.sound", soundClass.build())
                                .indent("    ")
                                .skipJavaLangImports(true)
                                .build()
                ),
                outputFolder
        );
    }
}
