// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.demo.grpc.proto;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_demo_grpc_proto_UserMessageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_demo_grpc_proto_UserMessageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_demo_grpc_proto_UserMessageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_demo_grpc_proto_UserMessageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\022\023com.demo.grpc.proto\032\021commo" +
      "n_enum.proto\"d\n\022UserMessageRequest\022\r\n\005em" +
      "ail\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\022-\n\004type\030\003 \001(" +
      "\0162\037.com.demo.grpc.proto.ActionType\"m\n\023Us" +
      "erMessageResponse\022\r\n\005token\030\001 \001(\t\022\014\n\004code" +
      "\030\002 \001(\005\022\014\n\004desc\030\003 \001(\t\022+\n\006status\030\004 \001(\0162\033.c" +
      "om.demo.grpc.proto.Status*X\n\nActionType\022" +
      "\022\n\016UNKNOWN_ACTION\020\000\022\020\n\014LOGIN_ACTION\020\001\022\023\n" +
      "\017REGISTER_ACTION\020\002\022\017\n\013CHECK_EMAIL\020\0032l\n\013U" +
      "serService\022]\n\010UserSend\022\'.com.demo.grpc.p",
      "roto.UserMessageRequest\032(.com.demo.grpc." +
      "proto.UserMessageResponseB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.demo.grpc.proto.CommonEnum.getDescriptor(),
        }, assigner);
    internal_static_com_demo_grpc_proto_UserMessageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_demo_grpc_proto_UserMessageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_demo_grpc_proto_UserMessageRequest_descriptor,
        new java.lang.String[] { "Email", "Password", "Type", });
    internal_static_com_demo_grpc_proto_UserMessageResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_demo_grpc_proto_UserMessageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_demo_grpc_proto_UserMessageResponse_descriptor,
        new java.lang.String[] { "Token", "Code", "Desc", "Status", });
    com.demo.grpc.proto.CommonEnum.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}