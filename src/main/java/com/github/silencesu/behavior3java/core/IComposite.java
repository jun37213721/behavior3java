package com.github.silencesu.behavior3java.core;

/**
 * 组合节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public interface IComposite {
    /**
     * 子节点count
     */
    int getChildCount();

    /**
     * 根据id 索引 子节点
     */
    BaseNode getChild(int index);

    /**
     * 增加一个子节点
     */
    void addChild(BaseNode baseNode);
}
