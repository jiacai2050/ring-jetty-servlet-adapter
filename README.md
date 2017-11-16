## Ring Jetty servlet adapter [![Clojars Project](https://img.shields.io/clojars/v/ring-jetty-servlet-adapter.svg)](https://clojars.org/ring-jetty-servlet-adapter)

Fork of [official jetty adapter](https://github.com/ring-clojure/ring/tree/master/ring-jetty-adapter) enhanced with servlet-mapping support.

## Why another adapter

Some Java libraries, such as [hystrix-metrics-event-stream](https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-metrics-event-stream), come with util servlets for exposing statistic infomation. But in a purely Ring-based app we can't use those directly, you must convert the servlet into a Ring handler function for use. This is cumbersome, and servlet support should be supported out of box. 

After [disscus with weavejester](https://github.com/ring-clojure/ring/pull/314), this adapter comes into world. If you are porting a Java web application into Ring, I think you can benefit from this adapter too.

The codebase stays in step with the official, with version number unchanged. So you can replace the official adapter with this in the least amount of effort.


## Install

```
[ring-jetty-servlet-adapter "1.6.3"]
```

## Usage

This adapter add a new option `:servlet-mapping`, here is an example:

```
{:servlet-mapping {"/hello" (HelloServlet.)}}
```
With this, `/hello` request will be dealt with `HelloServlet`, all other requests will be passed to your handler as normal.

## A minimal application

### core.clj
```
(ns demo.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:import [HelloServlet]))

(defroutes app
  (GET "/" [] "<h1>Index page</h1>")
  (GET "/about" [] "<h1>About page</h1>"))

(defn -main [& args]
  (jetty/run-jetty app {:port 3000
                        :servlet-mapping {"/foo" (HelloServlet. "foo")
                                          "/bar" (HelloServlet. "bar")}}))
```

### Helloservlet.java

```
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private String name;
    public HelloServlet(String name) {
        this.name = name;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Hello " + this.name);
    }
}
```
