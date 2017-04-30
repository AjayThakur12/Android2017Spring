package com.codingblocks.myapplication;

/**
 * Created by championswimmer on 30/04/17.
 */

public interface MyProviderContract {

    String CONTENT_URI = "content://myapp";

    interface Columns {
        String ID = "id";
        String NAME = "name";
    }
}
