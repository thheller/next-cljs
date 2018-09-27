(ns shadow.next-js
  (:require [goog.object :as gobj]))

(defn add-query [page-fn query-fn]
  (gobj/set page-fn "getInitialProps" (fn [req]
                                        (-> (query-fn req)
                                            ;; SSR transforms all CLJS data to JSON
                                            ;; which makes it pretty much useless
                                            ;; this is terrible but oh well ...
                                            (.then clj->js)))))
