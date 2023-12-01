package com.github.silencesu.behavior3java.core;

import lombok.Data;

/**
 * Tick
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Data
public class Tick {
    private BehaviorTree tree;

    private Blackboard blackboard;

    private Object target;

    public Tick(BehaviorTree tree, Blackboard blackboard, Object target) {
        this.tree = tree;
        this.blackboard = blackboard;
        this.target = target;
    }

    public void enterNode(BaseNode node) {

    }

    public void openNode(BaseNode node) {

    }

    public void tickNode(BaseNode node) {

    }

    public void exitNNode(BaseNode node) {

    }
}
