// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: gRPC-2020/calculator.proto

package sr.grpc.gen;

/**
 * Protobuf type {@code calculator.MultiplyArguments}
 */
public  final class MultiplyArguments extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:calculator.MultiplyArguments)
    MultiplyArgumentsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MultiplyArguments.newBuilder() to construct.
  private MultiplyArguments(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MultiplyArguments() {
    numbers_ = emptyIntList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MultiplyArguments();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MultiplyArguments(
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
          case 8: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              numbers_ = newIntList();
              mutable_bitField0_ |= 0x00000001;
            }
            numbers_.addInt(input.readInt32());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              numbers_ = newIntList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              numbers_.addInt(input.readInt32());
            }
            input.popLimit(limit);
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
        numbers_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.CalculatorProto.internal_static_calculator_MultiplyArguments_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.CalculatorProto.internal_static_calculator_MultiplyArguments_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.MultiplyArguments.class, sr.grpc.gen.MultiplyArguments.Builder.class);
  }

  public static final int NUMBERS_FIELD_NUMBER = 1;
  private com.google.protobuf.Internal.IntList numbers_;
  /**
   * <code>repeated int32 numbers = 1;</code>
   * @return A list containing the numbers.
   */
  public java.util.List<java.lang.Integer>
      getNumbersList() {
    return numbers_;
  }
  /**
   * <code>repeated int32 numbers = 1;</code>
   * @return The count of numbers.
   */
  public int getNumbersCount() {
    return numbers_.size();
  }
  /**
   * <code>repeated int32 numbers = 1;</code>
   * @param index The index of the element to return.
   * @return The numbers at the given index.
   */
  public int getNumbers(int index) {
    return numbers_.getInt(index);
  }
  private int numbersMemoizedSerializedSize = -1;

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
    getSerializedSize();
    if (getNumbersList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(numbersMemoizedSerializedSize);
    }
    for (int i = 0; i < numbers_.size(); i++) {
      output.writeInt32NoTag(numbers_.getInt(i));
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
      for (int i = 0; i < numbers_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(numbers_.getInt(i));
      }
      size += dataSize;
      if (!getNumbersList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      numbersMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof sr.grpc.gen.MultiplyArguments)) {
      return super.equals(obj);
    }
    sr.grpc.gen.MultiplyArguments other = (sr.grpc.gen.MultiplyArguments) obj;

    if (!getNumbersList()
        .equals(other.getNumbersList())) return false;
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
    if (getNumbersCount() > 0) {
      hash = (37 * hash) + NUMBERS_FIELD_NUMBER;
      hash = (53 * hash) + getNumbersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.MultiplyArguments parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.MultiplyArguments parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.MultiplyArguments parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.MultiplyArguments parseFrom(
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
  public static Builder newBuilder(sr.grpc.gen.MultiplyArguments prototype) {
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
   * Protobuf type {@code calculator.MultiplyArguments}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:calculator.MultiplyArguments)
      sr.grpc.gen.MultiplyArgumentsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.CalculatorProto.internal_static_calculator_MultiplyArguments_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.CalculatorProto.internal_static_calculator_MultiplyArguments_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.MultiplyArguments.class, sr.grpc.gen.MultiplyArguments.Builder.class);
    }

    // Construct using sr.grpc.gen.MultiplyArguments.newBuilder()
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
      numbers_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.CalculatorProto.internal_static_calculator_MultiplyArguments_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.MultiplyArguments getDefaultInstanceForType() {
      return sr.grpc.gen.MultiplyArguments.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.MultiplyArguments build() {
      sr.grpc.gen.MultiplyArguments result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.MultiplyArguments buildPartial() {
      sr.grpc.gen.MultiplyArguments result = new sr.grpc.gen.MultiplyArguments(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        numbers_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.numbers_ = numbers_;
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
      if (other instanceof sr.grpc.gen.MultiplyArguments) {
        return mergeFrom((sr.grpc.gen.MultiplyArguments)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.MultiplyArguments other) {
      if (other == sr.grpc.gen.MultiplyArguments.getDefaultInstance()) return this;
      if (!other.numbers_.isEmpty()) {
        if (numbers_.isEmpty()) {
          numbers_ = other.numbers_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureNumbersIsMutable();
          numbers_.addAll(other.numbers_);
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
      sr.grpc.gen.MultiplyArguments parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sr.grpc.gen.MultiplyArguments) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.IntList numbers_ = emptyIntList();
    private void ensureNumbersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        numbers_ = mutableCopy(numbers_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @return A list containing the numbers.
     */
    public java.util.List<java.lang.Integer>
        getNumbersList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(numbers_) : numbers_;
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @return The count of numbers.
     */
    public int getNumbersCount() {
      return numbers_.size();
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @param index The index of the element to return.
     * @return The numbers at the given index.
     */
    public int getNumbers(int index) {
      return numbers_.getInt(index);
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @param index The index to set the value at.
     * @param value The numbers to set.
     * @return This builder for chaining.
     */
    public Builder setNumbers(
        int index, int value) {
      ensureNumbersIsMutable();
      numbers_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @param value The numbers to add.
     * @return This builder for chaining.
     */
    public Builder addNumbers(int value) {
      ensureNumbersIsMutable();
      numbers_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @param values The numbers to add.
     * @return This builder for chaining.
     */
    public Builder addAllNumbers(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureNumbersIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, numbers_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 numbers = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumbers() {
      numbers_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000001);
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


    // @@protoc_insertion_point(builder_scope:calculator.MultiplyArguments)
  }

  // @@protoc_insertion_point(class_scope:calculator.MultiplyArguments)
  private static final sr.grpc.gen.MultiplyArguments DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.MultiplyArguments();
  }

  public static sr.grpc.gen.MultiplyArguments getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MultiplyArguments>
      PARSER = new com.google.protobuf.AbstractParser<MultiplyArguments>() {
    @java.lang.Override
    public MultiplyArguments parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MultiplyArguments(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MultiplyArguments> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MultiplyArguments> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.MultiplyArguments getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

