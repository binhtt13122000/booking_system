// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.demo.grpc.proto;

public interface UserMessageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.demo.grpc.proto.UserMessageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string email = 1;</code>
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 1;</code>
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>string password = 2;</code>
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 2;</code>
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>.com.demo.grpc.proto.ActionType type = 3;</code>
   */
  int getTypeValue();
  /**
   * <code>.com.demo.grpc.proto.ActionType type = 3;</code>
   */
  com.demo.grpc.proto.ActionType getType();
}
