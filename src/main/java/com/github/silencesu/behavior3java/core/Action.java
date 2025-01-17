package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Const;

/**
 * 行为节点  叶节点
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public abstract  class Action extends BaseNode  implements IAction {
    @Override
    public String getCategory() {
        return B3Const.ACTION;
    }

}
