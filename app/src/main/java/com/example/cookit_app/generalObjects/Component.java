package com.example.cookit_app.generalObjects;

public class Component {

    private String component, amount;

    public Component(String component, String amount) {
        this.component = component;
        this.amount = amount;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
