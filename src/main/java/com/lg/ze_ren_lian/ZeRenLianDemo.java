package com.lg.ze_ren_lian;

/**
 * 责任链模式
 *
 * @author Xulg
 * Created in 2019-11-07 9:54
 */
public class ZeRenLianDemo {

    /**
     * 关于责任链模式，其有两种形式
     *  1.一种通过外部调用的方式对链的各个节点调用进行控制，从而进行链的各个节点之间的切换。
     *              对于外部控制的方式，这种方式比较简单，链的每个节点只需要专注于各自的逻辑即可，
     *          而当前节点调用完成之后是否继续调用下一个节点，这个则由外部控制逻辑进行。这里我们以
     *          一个过滤器的实现逻辑为例进行讲解，在平常工作中，我们经常需要根据一系列的条件对某个
     *          东西进行过滤。
     *              比如任务服务的设计，在执行某个任务时，其需要经过诸如时效性检验，风控拦截，任务
     *          完成次数等过滤条件的检验之后才能判断当前任务是否能够执行，只有在所有的过滤条件都完
     *          成之后，我们才能执行该任务。那么这里我们就可以抽象出一个Filter接口，其设计如下：
     *                  public interface Filter {
     *                      // 用于对各个任务节点进行过滤
     *                      boolean filter(Task task);
     *                  }
     *              这里的Filter.filter()方法只有一个参数Task，主要就是控制当前task是否需要被过滤掉，
     *          其有一个boolean类型的返回值，通过该返回值以告知外部控制逻辑是否需要将该task过滤掉。对
     *          于该接口的子类，我们只需要将其声明为Spring所管理的一个bean即可：
     *                  // 时效性检验
     *                  @Component
     *                  public class DurationFilter implements Filter {
     *                      @Override
     *                      public boolean filter(Task task) {
     *                          System.out.println("时效性检验");
     *                          return true;
     *                      }
     *                  }
     *
     *                  // 风控拦截
     *                  @Component
     *                  public class RiskFilter implements Filter {
     *                      @Override
     *                      public boolean filter(Task task) {
     *                          System.out.println("风控拦截");
     *                      return true;
     *                      }
     *                  }
     *
     *                  // 次数限制校验
     *                  @Component
     *                  public class TimesFilter implements Filter {
     *                      @Override
     *                      public boolean filter(Task task) {
     *                          System.out.println("次数限制检验");
     *                          return true;
     *                      }
     *                  }
     *              上面我们模拟声明了三个Filter的子类，用于设计一系列的控制当前task是否需要被过滤的逻辑，
     *          结构上的逻辑其实比较简单，主要就是需要将其声明为Spring所管理的一个bean。下面是我们的控制逻
     *          辑:
     *                  @Service
     *                  public class ApplicationService {
     *                      @Autowired
     *                      private List<Filter> filters;
     *
     *                      public void mockedClient() {
     *                          // 这里task一般是通过数据库查询得到的
     *                          Task task = new Task();
     *                          for (Filter filter : filters) {
     *                              if (!filter.filter(task)) {
     *                                  return;
     *                              }
     *                          }
     *                          // 过滤完成，后续是执行任务的逻辑
     *                      }
     *                  }
     *              这种责任链设计方式的优点在于链的控制比较简单，只需要实现一个统一的接口即可，其基本上能够
     *          满足大部分的逻辑控制，但是对于某些需要动态调整链的需求其就无能为力了。
     *
     *  2.另一种是链的每个节点自由控制是否继续往下传递链的进度，这种比较典型的使用方式就是Netty中的责任链模式。
     */
}