package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;

/**
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public interface INode {
    void initialize(BTNodeCfg nodeCfg);

    String getCategory();

    String getName();

    String getTitle();

    B3Status execute(Tick tick);
}
