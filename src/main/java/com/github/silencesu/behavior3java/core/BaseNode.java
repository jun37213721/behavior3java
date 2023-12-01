package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import lombok.Data;

import java.util.Map;

/**
 * 基础节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Data
public abstract class BaseNode implements INode {
    private String id;
    private String name;
    private String title;
    private String description;

    private Map<String, String> properties;

    protected BehaviorTreeProject projectInfo;


    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        this.id = nodeCfg.getId();
        this.name = nodeCfg.getName();
        this.title = nodeCfg.getTitle();
        this.description = nodeCfg.getDescription();
        this.properties = nodeCfg.getProperties();
    }

    @Override
    public B3Status execute(Tick tick) {
        this.enter(tick);

        if (!isOpened(tick)) {
            open(tick);
        }

        B3Status status = this.tick(tick);
        this.exit(tick);
        return status;
    }

    protected void enter(Tick tick) {
        tick.enterNode(this);
        this.onEnter(tick);
    }

    protected void onEnter(Tick tick) {

    }

    protected boolean isOpened(Tick tick) {
        return tick.getBlackboard().getNodeMemeory(tick.getTree().getId(), this.getId()).isOpened();
    }

    /**
     * 首次tick前会open
     */
    protected void open(Tick tick) {
        tick.getBlackboard().getNodeMemeory(tick.getTree().getId(), this.getId()).setOpened(true);
        tick.openNode(this);
        onOpen(tick);
    }

    protected void onOpen(Tick tick) {

    }

    protected B3Status tick(Tick tick) {
        tick.tickNode(this);
        return this.onTick(tick);
    }

    protected abstract B3Status onTick(Tick tick);

    protected void exit(Tick tick) {
        tick.exitNNode(this);
        this.onExit(tick);
    }

    protected void onExit(Tick tick) {

    }
}
