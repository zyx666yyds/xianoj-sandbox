package com.zyx.zyxojsandbox.utils;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.zyx.zyxojsandbox.model.ExecuteMessage;

import java.io.*;

/**
 * 进程工具类
 *
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 17:47
 */
public class ProcessUtils {

    /**
     * 执行进程并获取信息
     *
     * @param compileProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process compileProcess, String opName) {
        ExecuteMessage result = new ExecuteMessage();

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int exitValue = compileProcess.waitFor();
            result.setExitValue(exitValue);

            if (exitValue == 0) {
                System.out.println(opName + "成功");
                //分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 获取编译结果
                String compileResult;
                while ((compileResult = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileResult).append("\n");
                }
                result.setMessage(compileOutputStringBuilder.toString());
            } else {
                System.out.println(opName + "失败" + exitValue);
                //分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                // 获取编译结果
                String compileResult;
                while ((compileResult = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileResult).append("\n");
                }

                //分批获取进程的错误输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
                StringBuilder compileErrorOutputStringBuilder = new StringBuilder();
                // 获取编译结果
                String errorCompileResult;
                while ((errorCompileResult = errorBufferedReader.readLine()) != null) {
                    compileErrorOutputStringBuilder.append(errorCompileResult).append("\n");
                }
                result.setErrorMessage(compileErrorOutputStringBuilder.toString());

            }
            stopWatch.stop();
            result.setTime(stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 执行交互式进程并获取信息
     *
     * @param compileProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runReactProcessAndGetMessage(Process compileProcess, String opName, String args) {
        ExecuteMessage result = new ExecuteMessage();

        try {
            InputStream inputStream = compileProcess.getInputStream();
            OutputStream outputStream = compileProcess.getOutputStream();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            String[] split = args.split(",");
            outputStreamWriter.write(StrUtil.join("\n", split) + "\n");

            outputStreamWriter.flush();

            //分批获取进程的正常输出
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder compileOutputStringBuilder = new StringBuilder();
            // 获取编译结果
            String compileResult;
            while ((compileResult = bufferedReader.readLine()) != null) {
                compileOutputStringBuilder.append(compileResult);
            }
            result.setMessage(compileOutputStringBuilder.toString());
            //资源回收
            inputStream.close();
            outputStream.close();
            outputStreamWriter.close();
            compileProcess.destroy();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
