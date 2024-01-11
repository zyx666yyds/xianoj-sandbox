package com.zyx.zyxojsandbox.docker;

import com.github.dockerjava.api.DockerClient;

import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.github.dockerjava.transport.DockerHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/9 009 20:16
 */
public class DockerDemo {

    public static void main(String[] args) throws InterruptedException, IOException {


        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://114.55.110.155:2376").build();

        String image = "nginx:latest";
//        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(name);
//        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
//
//            @Override
//            public void onNext(PullResponseItem item) {
//                System.out.println("下载镜像：" + item.getStatus());
//                super.onNext(item);
//            }
//        };
//        pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
//        System.out.println("下载完成");
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
        CreateContainerResponse createContainerResponse = containerCmd.withCmd("echo", "Hello 飞~~~").exec();
        System.out.println(createContainerResponse);
        String responseId = createContainerResponse.getId();

        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
        List<Container> exec = listContainersCmd.withShowAll(true).exec();
        for (Container container : exec) {
            System.out.println(container);
        }

        dockerClient.startContainerCmd(responseId).exec();

        LogContainerResultCallback logContainerResultCallback = new LogContainerResultCallback() {
            @Override
            public void onNext(Frame item) {
                System.out.println("日志：" + item.getPayload().toString());
                super.onNext(item);
            }
        };
        dockerClient.logContainerCmd(responseId)
                .withStdErr(true)
                .withStdOut(true)
                .exec(logContainerResultCallback)
                .awaitCompletion();
    }
}
