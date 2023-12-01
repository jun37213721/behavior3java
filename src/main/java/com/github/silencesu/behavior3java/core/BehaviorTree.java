package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.DefaultNodes;
import com.github.silencesu.behavior3java.constant.B3Const;
import com.github.silencesu.behavior3java.constant.B3Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 行为树
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Getter
@Setter
@Slf4j
public class BehaviorTree {
    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    private String titile;
    private String description;
    private Map<String, Object> properties = new HashMap<>();

    private BaseNode root;

    //project 引用对象
    private BehaviorTreeProject projectInfo;

    public void load(BTTreeCfg cfg) throws InstantiationException, IllegalAccessException {
        load(cfg, new HashMap<>());
    }

    public void load(BTTreeCfg cfg, Map<String, Class<? extends BaseNode>> extendNodes) throws InstantiationException, IllegalAccessException {
        this.titile = cfg.getTitle();
        this.description = cfg.getDescription();
        this.properties = cfg.getProperties();

        Map<String, Class<? extends BaseNode>> nodeMaps = new HashMap<>(DefaultNodes.get());
        //加载扩展nodes
        if (extendNodes != null && !extendNodes.isEmpty()) {
            nodeMaps.putAll(extendNodes);
        }

        //create  nodes
        Map<String, BaseNode> nodes = new HashMap<>();
        for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
            String id = nodeEntry.getKey();
            BTNodeCfg nodeCfg = nodeEntry.getValue();
            BaseNode node = null;

            //检查是或否为子树
            if (nodeCfg.getCategory()!=null && nodeCfg.getCategory().equals(B3Const.SUBTREE)) {
                node = new SubTree();
            } else {
                //普通结点加载
                Class<? extends BaseNode> clazz = nodeMaps.get(nodeCfg.getName());
                node = clazz.newInstance();
            }

            node.initialize(nodeCfg);

            if (projectInfo != null) {
                node.setProjectInfo(projectInfo);
            }

            nodes.put(id, node);
        }

        // connect the nodes
        for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
            BaseNode node = nodes.get(nodeEntry.getKey());
            BTNodeCfg nodeCfg = nodeEntry.getValue();

            if (node.getCategory().equals(B3Const.COMPOSITE) && nodeCfg.getChildren() != null) {
                for (String cid : nodeCfg.getChildren()) {
                    IComposite comp = (IComposite) node;
                    comp.addChild(nodes.get(cid));
                }

            } else if (node.getCategory().equals(B3Const.DECORATOR) && !nodeCfg.getChild().isEmpty()) {
                IDecorator deco = (IDecorator) node;
                deco.setChild(nodes.get(nodeCfg.getChild()));
            }
        }

        //设置root节点
        this.root = nodes.get(cfg.getRoot());
    }

    public B3Status tick(Tick tick) {
        return this.root.execute(tick);
    }
}
