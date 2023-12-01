package com.github.silencesu.behavior3java.core;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 黑板报
 * k-v存储数据
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Data
public class Blackboard {
    private Map<String, TreeMemory> treeMemory = new HashMap<>();

    private TreeMemory getTreeMemory(String treeScope) {
        return treeMemory.computeIfAbsent(treeScope, k -> new TreeMemory());
    }

    public TreeMemory.NodeMemory getNodeMemeory(String treeScope, String nodeScope) {
        TreeMemory tm = getTreeMemory(treeScope);
        return tm.getNodeMemory().computeIfAbsent(nodeScope, k -> new TreeMemory.NodeMemory());
    }

    public void setNodeMemory(String key, Object value, String treeScope, String nodeScope) {
        TreeMemory.NodeMemory memory = getNodeMemeory(treeScope, nodeScope);
        memory.getNodeData().put(key, value);
    }


    @SuppressWarnings("unchecked")
    public <T> T getNodeMemory(String key, String treeScope, String nodeScope) {
        TreeMemory.NodeMemory memory = getNodeMemeory(treeScope, nodeScope);
        Object object = memory.getNodeData().get(key);
        return (T) object;
    }

    @Data
    public static class TreeMemory {
        private Map<String, Object> treeData = new HashMap<>();
        private Map<String, NodeMemory> nodeMemory = new HashMap<>();

        @Data
        public static class NodeMemory {
            private boolean opened;
            private Map<String, Object> nodeData = new HashMap<>();
        }
    }
}
