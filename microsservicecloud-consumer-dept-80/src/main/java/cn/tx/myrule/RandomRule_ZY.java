package cn.tx.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRule_ZY extends AbstractLoadBalancerRule {

    //定义total =0;当total==5时 指针才能往下走
    //currentIndex=0,当前对外服务的服务器地址
    //total重置为0.currentIndex++ currentIndex=1

    //分析：每个机器调用5次 我们只有8001.8002,8003 三台
    //当currentIndex==3 时 currentIndex

    private int total=0;//被调用的次数，目前要求被调用5次

    private int currentIndex=0;//当前服务的机器号

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            // int index = chooseRandomInt(serverCount);
            // server = upList.get(index);
            if(total<5){
                server=upList.get(currentIndex);
                total++;
            }else {
                total=0;
                currentIndex++;
                if(currentIndex>allList.size()){
                    currentIndex=0;
                }
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}

