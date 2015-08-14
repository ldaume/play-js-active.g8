package controllers;

import play.libs.EventSource;
import play.libs.F;
import views.html.*;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.concurrent.TimeUnit;

public class Application extends Controller {

  public Result index() {
    return ok(index.render("Hello Play Framework"));
  }

  public Result syncFoo() {
    return ok("sync foo");
  }

  public F.Promise<Result> asyncFoo() {
    return F.Promise.promise(() -> ok("async foo"));
  }

  public F.Promise<Result> asyncNonBlockingFoo() {
    return F.Promise.delayed(() -> ok("async non-blocking foo"), 5, TimeUnit.SECONDS);
  }

  public F.Promise<Result> reactiveRequest() {
    F.Promise<WSResponse> typesafePromise = WS.url("http://www.typesafe.com").get();
    return typesafePromise.map(response -> ok(response.getBody()));
  }

  public F.Promise<Result> reactiveComposition() {
    final F.Promise<WSResponse> twitterPromise = WS.url("http://www.twitter.com").get();
    final F.Promise<WSResponse> typesafePromise = WS.url("http://www.typesafe.com").get();

    return twitterPromise.flatMap((twitter) -> typesafePromise.map((typesafe) -> ok(twitter
                                                                                      .getBody()
                                                                                    + typesafe
                                                                                      .getBody())));
  }

  public Result events() {
    EventSource eventSource = new EventSource() {
      public void onConnected() {
        send(Event.event("hello"));
      }
    };
    return ok(eventSource);
  }

  public WebSocket<String> echo() {
    return new WebSocket<String>() {
      public void onReady(final In<String> in, final Out<String> out) {
        in.onMessage(out::write);
      }
    };
  }
}