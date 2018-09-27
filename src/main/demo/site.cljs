(ns demo.site
  (:require
    [reagent.core :as r]
    [shadow.next-js :as sn]
    ["next/link" :default Link]))

(defn page-index
  {:export true
   :next/page "index"}
  [props]
  (r/as-element
    [:div
     [:h1 "Hi people!"]

     [:p "Welcome to your new next.js site."]
     [:p "Now go build something great with ClojureScript."]

     [:> Link {:href "/page-2"} [:a "goto page-2"]]
     ]))

(defn page-index-query [req]
  (js/Promise.resolve {:hello "world"}))

(sn/add-query page-index page-index-query)

(defn page-2
  {:export true
   :next/page "page-2"}
  [props]
  (r/as-element
    [:div
     [:h1 "Hello from Page 2"]

     [:> Link {:href "/"} [:a "back to index"]]
     ]))

(defn page-404
  {:export true
   :next/page "404"}
  [props]
  (r/as-element
    [:h1 "404!"]))