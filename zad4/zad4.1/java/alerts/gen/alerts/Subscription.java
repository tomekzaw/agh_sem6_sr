// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: alerts.proto

package alerts;

/**
 * Protobuf type {@code Subscription}
 */
public  final class Subscription extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Subscription)
    SubscriptionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Subscription.newBuilder() to construct.
  private Subscription(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Subscription() {
    hashtags_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Subscription();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Subscription(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              hashtags_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            hashtags_.add(s);
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        hashtags_ = hashtags_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return alerts.AlertsOuterClass.internal_static_Subscription_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return alerts.AlertsOuterClass.internal_static_Subscription_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            alerts.Subscription.class, alerts.Subscription.Builder.class);
  }

  public static final int HASHTAGS_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList hashtags_;
  /**
   * <code>repeated string hashtags = 1;</code>
   * @return A list containing the hashtags.
   */
  public com.google.protobuf.ProtocolStringList
      getHashtagsList() {
    return hashtags_;
  }
  /**
   * <code>repeated string hashtags = 1;</code>
   * @return The count of hashtags.
   */
  public int getHashtagsCount() {
    return hashtags_.size();
  }
  /**
   * <code>repeated string hashtags = 1;</code>
   * @param index The index of the element to return.
   * @return The hashtags at the given index.
   */
  public java.lang.String getHashtags(int index) {
    return hashtags_.get(index);
  }
  /**
   * <code>repeated string hashtags = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the hashtags at the given index.
   */
  public com.google.protobuf.ByteString
      getHashtagsBytes(int index) {
    return hashtags_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < hashtags_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, hashtags_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < hashtags_.size(); i++) {
        dataSize += computeStringSizeNoTag(hashtags_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getHashtagsList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof alerts.Subscription)) {
      return super.equals(obj);
    }
    alerts.Subscription other = (alerts.Subscription) obj;

    if (!getHashtagsList()
        .equals(other.getHashtagsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getHashtagsCount() > 0) {
      hash = (37 * hash) + HASHTAGS_FIELD_NUMBER;
      hash = (53 * hash) + getHashtagsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static alerts.Subscription parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alerts.Subscription parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alerts.Subscription parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alerts.Subscription parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alerts.Subscription parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static alerts.Subscription parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static alerts.Subscription parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alerts.Subscription parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static alerts.Subscription parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static alerts.Subscription parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static alerts.Subscription parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static alerts.Subscription parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(alerts.Subscription prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code Subscription}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Subscription)
      alerts.SubscriptionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return alerts.AlertsOuterClass.internal_static_Subscription_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return alerts.AlertsOuterClass.internal_static_Subscription_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              alerts.Subscription.class, alerts.Subscription.Builder.class);
    }

    // Construct using alerts.Subscription.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      hashtags_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return alerts.AlertsOuterClass.internal_static_Subscription_descriptor;
    }

    @java.lang.Override
    public alerts.Subscription getDefaultInstanceForType() {
      return alerts.Subscription.getDefaultInstance();
    }

    @java.lang.Override
    public alerts.Subscription build() {
      alerts.Subscription result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public alerts.Subscription buildPartial() {
      alerts.Subscription result = new alerts.Subscription(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        hashtags_ = hashtags_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.hashtags_ = hashtags_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof alerts.Subscription) {
        return mergeFrom((alerts.Subscription)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(alerts.Subscription other) {
      if (other == alerts.Subscription.getDefaultInstance()) return this;
      if (!other.hashtags_.isEmpty()) {
        if (hashtags_.isEmpty()) {
          hashtags_ = other.hashtags_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureHashtagsIsMutable();
          hashtags_.addAll(other.hashtags_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      alerts.Subscription parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (alerts.Subscription) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList hashtags_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureHashtagsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        hashtags_ = new com.google.protobuf.LazyStringArrayList(hashtags_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @return A list containing the hashtags.
     */
    public com.google.protobuf.ProtocolStringList
        getHashtagsList() {
      return hashtags_.getUnmodifiableView();
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @return The count of hashtags.
     */
    public int getHashtagsCount() {
      return hashtags_.size();
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param index The index of the element to return.
     * @return The hashtags at the given index.
     */
    public java.lang.String getHashtags(int index) {
      return hashtags_.get(index);
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the hashtags at the given index.
     */
    public com.google.protobuf.ByteString
        getHashtagsBytes(int index) {
      return hashtags_.getByteString(index);
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param index The index to set the value at.
     * @param value The hashtags to set.
     * @return This builder for chaining.
     */
    public Builder setHashtags(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureHashtagsIsMutable();
      hashtags_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param value The hashtags to add.
     * @return This builder for chaining.
     */
    public Builder addHashtags(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureHashtagsIsMutable();
      hashtags_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param values The hashtags to add.
     * @return This builder for chaining.
     */
    public Builder addAllHashtags(
        java.lang.Iterable<java.lang.String> values) {
      ensureHashtagsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, hashtags_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearHashtags() {
      hashtags_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string hashtags = 1;</code>
     * @param value The bytes of the hashtags to add.
     * @return This builder for chaining.
     */
    public Builder addHashtagsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureHashtagsIsMutable();
      hashtags_.add(value);
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Subscription)
  }

  // @@protoc_insertion_point(class_scope:Subscription)
  private static final alerts.Subscription DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new alerts.Subscription();
  }

  public static alerts.Subscription getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Subscription>
      PARSER = new com.google.protobuf.AbstractParser<Subscription>() {
    @java.lang.Override
    public Subscription parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Subscription(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Subscription> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Subscription> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public alerts.Subscription getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

