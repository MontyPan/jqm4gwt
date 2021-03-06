package com.sksamuel.jqm4gwt.plugins.datatables;

import com.google.gwt.core.client.Callback;
import com.sksamuel.jqm4gwt.ScriptUtils;
import com.sksamuel.jqm4gwt.ScriptUtils.InjectCallback;

public interface Resources {

    class Loader {

        /**
         * Must be set to true explicitly in static constructor of your application
         * in case you are going to manage JS and CSS loading manually, e.g. combined files.
         **/
        public static boolean manualLoading;

        /**
         * Must be set to true explicitly in static constructor of your application if you need
         * default visual support for <a href="https://www.datatables.net/examples/api/row_details.html">
         * Show extra/detailed row information</a>
         **/
        public static boolean loadCssRowDetails;

        public static boolean loadFooterOnTop;

        private static final String DATATABLES_CSS = "css/jquery.dataTables.min.css";
        private static final String DATATABLES_CSS_ADJUST = "css/dataTables-adjust.css";
        private static final String DATATABLES_CSS_FOOTER_ON_TOP = "css/dataTables-footer-on-top.css";
        private static final String DATATABLES_CSS_ROW_DETAILS = "css/dataTables-row-details.css";
        private static final String DATATABLES_JS = "js/jquery.dataTables.min.js";
        private static final String DATATABLES_API_JS = "js/jquery.dataTables.api.js";

        public static void injectAll(final InjectCallback done) {
            ScriptUtils.waitJqmLoaded(new Callback<Void, Throwable>() {

                @Override
                public void onSuccess(Void result) {
                    if (manualLoading) {
                        if (done != null) done.onSuccess(null);
                        return;
                    }
                    ScriptUtils.injectCss(DATATABLES_CSS, DATATABLES_CSS_ADJUST);
                    if (loadCssRowDetails) ScriptUtils.injectCss(DATATABLES_CSS_ROW_DETAILS);
                    if (loadFooterOnTop) ScriptUtils.injectCss(DATATABLES_CSS_FOOTER_ON_TOP);
                    ScriptUtils.injectJs(done, DATATABLES_JS, DATATABLES_API_JS);
                }

                @Override
                public void onFailure(Throwable reason) {
                    throw new RuntimeException(reason);
                }
            });
        }
    }

}
