package com.linglingyi.com.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/17
 */
public class MultiBindCard extends AbstractExpandableItem<BindCard> {
    @Override
    public int getLevel() {
        return 1;
    }
}
