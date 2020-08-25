## Simple application for bug reporting

This application demonstrates a bug in micronaut `RxProxyHttpClient`.

When the downstream service responds without body, no response is returned back to the caller.
Also, no exception is thrown.
