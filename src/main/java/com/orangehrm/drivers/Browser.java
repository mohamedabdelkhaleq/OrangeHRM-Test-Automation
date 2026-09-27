package com.orangehrm.drivers;

import org.openqa.selenium.WebDriver;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FireFoxFactory();
        }
    },
    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}
