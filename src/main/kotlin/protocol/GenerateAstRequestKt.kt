// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: request.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package protocol;

@kotlin.jvm.JvmName("-initializegenerateAstRequest")
public inline fun generateAstRequest(block: protocol.GenerateAstRequestKt.Dsl.() -> kotlin.Unit): protocol.Request.GenerateAstRequest =
  protocol.GenerateAstRequestKt.Dsl._create(protocol.Request.GenerateAstRequest.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `protocol.GenerateAstRequest`
 */
public object GenerateAstRequestKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: protocol.Request.GenerateAstRequest.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: protocol.Request.GenerateAstRequest.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): protocol.Request.GenerateAstRequest = _builder.build()

    /**
     * `string code = 1;`
     */
    public var code: kotlin.String
      @JvmName("getCode")
      get() = _builder.getCode()
      @JvmName("setCode")
      set(value) {
        _builder.setCode(value)
      }
    /**
     * `string code = 1;`
     */
    public fun clearCode() {
      _builder.clearCode()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun protocol.Request.GenerateAstRequest.copy(block: `protocol`.GenerateAstRequestKt.Dsl.() -> kotlin.Unit): protocol.Request.GenerateAstRequest =
  `protocol`.GenerateAstRequestKt.Dsl._create(this.toBuilder()).apply { block() }._build()

