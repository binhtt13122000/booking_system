// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: search_booking.proto

package com.demo.grpc.proto;

/**
 * Protobuf type {@code com.demo.grpc.proto.SearchMessageRequest}
 */
public  final class SearchMessageRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.demo.grpc.proto.SearchMessageRequest)
    SearchMessageRequestOrBuilder {
  // Use SearchMessageRequest.newBuilder() to construct.
  private SearchMessageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SearchMessageRequest() {
    guestId_ = 0;
    page_ = 0;
    size_ = 0;
    isActive_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SearchMessageRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            guestId_ = input.readInt32();
            break;
          }
          case 16: {

            page_ = input.readInt32();
            break;
          }
          case 24: {

            size_ = input.readInt32();
            break;
          }
          case 32: {

            isActive_ = input.readBool();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.demo.grpc.proto.SearchBooking.internal_static_com_demo_grpc_proto_SearchMessageRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.demo.grpc.proto.SearchBooking.internal_static_com_demo_grpc_proto_SearchMessageRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.demo.grpc.proto.SearchMessageRequest.class, com.demo.grpc.proto.SearchMessageRequest.Builder.class);
  }

  public static final int GUESTID_FIELD_NUMBER = 1;
  private int guestId_;
  /**
   * <code>int32 guestId = 1;</code>
   */
  public int getGuestId() {
    return guestId_;
  }

  public static final int PAGE_FIELD_NUMBER = 2;
  private int page_;
  /**
   * <code>int32 page = 2;</code>
   */
  public int getPage() {
    return page_;
  }

  public static final int SIZE_FIELD_NUMBER = 3;
  private int size_;
  /**
   * <code>int32 size = 3;</code>
   */
  public int getSize() {
    return size_;
  }

  public static final int ISACTIVE_FIELD_NUMBER = 4;
  private boolean isActive_;
  /**
   * <code>bool isActive = 4;</code>
   */
  public boolean getIsActive() {
    return isActive_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (guestId_ != 0) {
      output.writeInt32(1, guestId_);
    }
    if (page_ != 0) {
      output.writeInt32(2, page_);
    }
    if (size_ != 0) {
      output.writeInt32(3, size_);
    }
    if (isActive_ != false) {
      output.writeBool(4, isActive_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (guestId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, guestId_);
    }
    if (page_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, page_);
    }
    if (size_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, size_);
    }
    if (isActive_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(4, isActive_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.demo.grpc.proto.SearchMessageRequest)) {
      return super.equals(obj);
    }
    com.demo.grpc.proto.SearchMessageRequest other = (com.demo.grpc.proto.SearchMessageRequest) obj;

    boolean result = true;
    result = result && (getGuestId()
        == other.getGuestId());
    result = result && (getPage()
        == other.getPage());
    result = result && (getSize()
        == other.getSize());
    result = result && (getIsActive()
        == other.getIsActive());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + GUESTID_FIELD_NUMBER;
    hash = (53 * hash) + getGuestId();
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + getPage();
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getSize();
    hash = (37 * hash) + ISACTIVE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsActive());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.demo.grpc.proto.SearchMessageRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.demo.grpc.proto.SearchMessageRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.demo.grpc.proto.SearchMessageRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.demo.grpc.proto.SearchMessageRequest)
      com.demo.grpc.proto.SearchMessageRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.demo.grpc.proto.SearchBooking.internal_static_com_demo_grpc_proto_SearchMessageRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.demo.grpc.proto.SearchBooking.internal_static_com_demo_grpc_proto_SearchMessageRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.demo.grpc.proto.SearchMessageRequest.class, com.demo.grpc.proto.SearchMessageRequest.Builder.class);
    }

    // Construct using com.demo.grpc.proto.SearchMessageRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      guestId_ = 0;

      page_ = 0;

      size_ = 0;

      isActive_ = false;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.demo.grpc.proto.SearchBooking.internal_static_com_demo_grpc_proto_SearchMessageRequest_descriptor;
    }

    public com.demo.grpc.proto.SearchMessageRequest getDefaultInstanceForType() {
      return com.demo.grpc.proto.SearchMessageRequest.getDefaultInstance();
    }

    public com.demo.grpc.proto.SearchMessageRequest build() {
      com.demo.grpc.proto.SearchMessageRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.demo.grpc.proto.SearchMessageRequest buildPartial() {
      com.demo.grpc.proto.SearchMessageRequest result = new com.demo.grpc.proto.SearchMessageRequest(this);
      result.guestId_ = guestId_;
      result.page_ = page_;
      result.size_ = size_;
      result.isActive_ = isActive_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.demo.grpc.proto.SearchMessageRequest) {
        return mergeFrom((com.demo.grpc.proto.SearchMessageRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.demo.grpc.proto.SearchMessageRequest other) {
      if (other == com.demo.grpc.proto.SearchMessageRequest.getDefaultInstance()) return this;
      if (other.getGuestId() != 0) {
        setGuestId(other.getGuestId());
      }
      if (other.getPage() != 0) {
        setPage(other.getPage());
      }
      if (other.getSize() != 0) {
        setSize(other.getSize());
      }
      if (other.getIsActive() != false) {
        setIsActive(other.getIsActive());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.demo.grpc.proto.SearchMessageRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.demo.grpc.proto.SearchMessageRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int guestId_ ;
    /**
     * <code>int32 guestId = 1;</code>
     */
    public int getGuestId() {
      return guestId_;
    }
    /**
     * <code>int32 guestId = 1;</code>
     */
    public Builder setGuestId(int value) {
      
      guestId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 guestId = 1;</code>
     */
    public Builder clearGuestId() {
      
      guestId_ = 0;
      onChanged();
      return this;
    }

    private int page_ ;
    /**
     * <code>int32 page = 2;</code>
     */
    public int getPage() {
      return page_;
    }
    /**
     * <code>int32 page = 2;</code>
     */
    public Builder setPage(int value) {
      
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page = 2;</code>
     */
    public Builder clearPage() {
      
      page_ = 0;
      onChanged();
      return this;
    }

    private int size_ ;
    /**
     * <code>int32 size = 3;</code>
     */
    public int getSize() {
      return size_;
    }
    /**
     * <code>int32 size = 3;</code>
     */
    public Builder setSize(int value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 size = 3;</code>
     */
    public Builder clearSize() {
      
      size_ = 0;
      onChanged();
      return this;
    }

    private boolean isActive_ ;
    /**
     * <code>bool isActive = 4;</code>
     */
    public boolean getIsActive() {
      return isActive_;
    }
    /**
     * <code>bool isActive = 4;</code>
     */
    public Builder setIsActive(boolean value) {
      
      isActive_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool isActive = 4;</code>
     */
    public Builder clearIsActive() {
      
      isActive_ = false;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.demo.grpc.proto.SearchMessageRequest)
  }

  // @@protoc_insertion_point(class_scope:com.demo.grpc.proto.SearchMessageRequest)
  private static final com.demo.grpc.proto.SearchMessageRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.demo.grpc.proto.SearchMessageRequest();
  }

  public static com.demo.grpc.proto.SearchMessageRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SearchMessageRequest>
      PARSER = new com.google.protobuf.AbstractParser<SearchMessageRequest>() {
    public SearchMessageRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SearchMessageRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SearchMessageRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SearchMessageRequest> getParserForType() {
    return PARSER;
  }

  public com.demo.grpc.proto.SearchMessageRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

