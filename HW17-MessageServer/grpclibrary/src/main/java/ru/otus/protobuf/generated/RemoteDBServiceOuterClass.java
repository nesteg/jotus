// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RemoteDBService.proto

package ru.otus.protobuf.generated;

public final class RemoteDBServiceOuterClass {
  private RemoteDBServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ru_otus_protobuf_generated_UserMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ru_otus_protobuf_generated_UserMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ru_otus_protobuf_generated_IdMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ru_otus_protobuf_generated_IdMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ru_otus_protobuf_generated_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ru_otus_protobuf_generated_Empty_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025RemoteDBService.proto\022\032ru.otus.protobu" +
      "f.generated\"\'\n\013UserMessage\022\n\n\002id\030\001 \001(\003\022\014" +
      "\n\004name\030\002 \001(\t\"\027\n\tIdMessage\022\n\n\002id\030\001 \001(\003\"\007\n" +
      "\005Empty2\257\002\n\017RemoteMsService\022\\\n\010saveUser\022\'" +
      ".ru.otus.protobuf.generated.UserMessage\032" +
      "\'.ru.otus.protobuf.generated.UserMessage" +
      "\022\\\n\014findAllUsers\022!.ru.otus.protobuf.gene" +
      "rated.Empty\032\'.ru.otus.protobuf.generated" +
      ".UserMessage0\001\022`\n\014findByIdUser\022%.ru.otus" +
      ".protobuf.generated.IdMessage\032\'.ru.otus." +
      "protobuf.generated.UserMessage0\0012\267\002\n\017Rem" +
      "oteDBService\022`\n\010saveUser\022\'.ru.otus.proto" +
      "buf.generated.UserMessage\032\'.ru.otus.prot" +
      "obuf.generated.UserMessage(\0010\001\022^\n\014findAl" +
      "lUsers\022\'.ru.otus.protobuf.generated.User" +
      "Message\032!.ru.otus.protobuf.generated.Emp" +
      "ty(\0010\001\022b\n\014findByIdUser\022\'.ru.otus.protobu" +
      "f.generated.UserMessage\032%.ru.otus.protob" +
      "uf.generated.IdMessage(\0010\001B\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ru_otus_protobuf_generated_UserMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ru_otus_protobuf_generated_UserMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ru_otus_protobuf_generated_UserMessage_descriptor,
        new java.lang.String[] { "Id", "Name", });
    internal_static_ru_otus_protobuf_generated_IdMessage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ru_otus_protobuf_generated_IdMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ru_otus_protobuf_generated_IdMessage_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_ru_otus_protobuf_generated_Empty_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ru_otus_protobuf_generated_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ru_otus_protobuf_generated_Empty_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}