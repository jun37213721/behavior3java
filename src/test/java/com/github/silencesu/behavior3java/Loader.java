package com.github.silencesu.behavior3java;

import com.github.silencesu.behavior3java.actions.Log;
import com.github.silencesu.behavior3java.core.*;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试用例
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public class Loader {

    //自定义扩展log
    private static final Map<String, Class<? extends BaseNode>> extendNodes = new HashMap<String, Class<? extends BaseNode>>() {
        {
            put("Log", Log.class);
        }
    };


    @Test
    public void loadTree() throws IOException, URISyntaxException, InstantiationException, IllegalAccessException {
        Path path = Paths.get(Loader.class.getResource("/tree.json").toURI());
        String confJson = path.toString();
        BehaviorTree behaviorTree = B3Loader.loadB3Tree(confJson, extendNodes);
        Blackboard blackboard = new Blackboard();
        Tick tick = new Tick(behaviorTree, blackboard, new Object());
        behaviorTree.tick(tick);
    }

    @Test
    public void loadProject() throws IOException, URISyntaxException, InstantiationException, IllegalAccessException {
        Path path = Paths.get(Loader.class.getResource("/project.b3").toURI());
        String confJson = path.toString();
        BehaviorTreeProject behaviorTreeProject = B3Loader.loadB3Project(confJson, extendNodes);
        Blackboard blackboard = new Blackboard();
        BehaviorTree behaviorTree = behaviorTreeProject.findBTTreeByTitle("tree1");
        Tick tick = new Tick(behaviorTree, blackboard, new Object());
        behaviorTree.tick(tick);
    }
}
