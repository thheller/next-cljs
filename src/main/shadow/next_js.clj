(ns shadow.next-js
  (:require
    [clojure.java.io :as io]
    [cljs.compiler :as cljs-comp]
    [clojure.string :as str]))

(defn all-vars [state]
  (for [[ns ns-info] (get-in state [:compiler-env :cljs.analyzer/namespaces])
        ns-def (-> ns-info :defs vals)]
    ns-def))

(defn create-pages
  {:shadow.build/stage :flush}
  [state]
  (doseq [ns-def (all-vars state)
          :when (get-in ns-def [:meta :next/page])]

    (let [{:next/keys [page]}
          (:meta ns-def)

          page-ns
          (-> ns-def :name namespace cljs-comp/munge)

          page-var
          (-> ns-def :name name cljs-comp/munge)

          ;; Y U NO DATA THIS!
          content
          (str
            "\nexport {" page-var " as default} from \"../src/cljs/" page-ns ".js\";")

          out-dir
          (io/file "site" "pages")

          out-file
          (io/file out-dir (str page ".js"))]

      (io/make-parents out-file)
      (spit out-file content)
      ))
  state)