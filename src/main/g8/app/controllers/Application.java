package controllers;

import com.google.inject.Inject;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.webjars.play.WebJarsUtil;
import play.libs.concurrent.Futures;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Application extends Controller {


    private final WebJarsUtil webJarsUtil;

    private final HttpExecutionContext ec;

    private final WSClient ws;

    @Inject
    public Application(WebJarsUtil webJarsUtil, HttpExecutionContext ec, WSClient ws) {
        this.webJarsUtil = webJarsUtil;
        this.ec = ec;
        this.ws = ws;
    }

    public CompletionStage<Result> index() {
        return supplyAsync(() -> ok(views.html.index.render("Hello Play JS", webJarsUtil)), ec.current());
    }

    public Result syncFoo() {
        return ok("sync foo");
    }

    public CompletionStage<Result> asyncFoo() {
        return supplyAsync(() -> ok("async foo"));
    }

    public CompletionStage<Result> asyncNonBlockingFoo() {
        return Futures.delayed(() -> ok("async non-blocking foo"), 5, TimeUnit.SECONDS, ec.current());
    }

    public CompletionStage<Result> reactiveRequest() {
        return ws.url("http://www.typesafe.com")
                .setFollowRedirects(true)
                .get()
                .thenApplyAsync(response -> ok(Html.apply(response.getBody())));
    }

    public CompletionStage<Result> reactiveComposition() {

        return ws.url("http://www.twitter.com").setFollowRedirects(true)
                .get()
                .thenComposeAsync(twitter -> ws.url("http://www.typesafe.com")
                        .setFollowRedirects(true)
                        .get()
                        .thenApplyAsync(typesafe ->
                                ok(Html.apply(
                                        twitter.getBody()
                                                + typesafe.getBody()))
                        ));
    }
}
