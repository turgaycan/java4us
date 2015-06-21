package com.java4us.commons.decorator;

/**
 * Created by turgaycan on 6/21/15.
 */
public abstract class AbstractModelDecoratorBuilder {

    private AbstractModelDecorator first;
    private AbstractModelDecorator last;

    public AbstractModelDecoratorBuilder add(boolean condition, String anyAlias) {
        if (first == null) {
            first = new AbstractModelDecorator(condition, anyAlias);
            last = first;
        } else {
            last.setNext(new AbstractModelDecorator(condition, anyAlias));
            last = last.getNext();
        }
        return this;
    }

    public AbstractModelDecorator build() {
        return first;
    }

}