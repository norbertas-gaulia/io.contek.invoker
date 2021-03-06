package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Position;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

public class GetPosition extends UserRestRequest<GetPositions.Response>{

  private String instrumentName;

  GetPosition(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPosition setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/get_position";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instrumentName);
    builder.add("instrument_name", instrumentName);

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected Class<GetPositions.Response> getResponseType() {
    return GetPositions.Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Position> {
  }
}
