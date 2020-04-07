package com.ozx.ozxshopcommombasecore.transaction;

import com.ozx.ozxshopcommombasecore.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;


/**
 * @ClassName: RedisDataResourceTransaction
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/14 0:48
 * @Version： 1.0
 **/
@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class RedisDataResourceTransaction {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;


    /**
     * 开启数据库的事务，事务传播行为
     * @return
     */
    public TransactionStatus begin(){
        /**
         * 开启数据库事务，事务传播行为
         */
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        /**
         * 开启redis事务
         */
        redisUtil.begin();
        return transactionStatus;
    }

    /**
     * 提交事务
     */
    public void commit(TransactionStatus transactionStatus) throws Exception {
        if (transactionStatus == null) {
            throw new Exception("transactionStatus不能为空");
        }
//        redisUtil.exec();
        /**
         * 支持redis和数据库事务同时提交
         */
        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void rollback(TransactionStatus transactionStatus) throws Exception {
        if (transactionStatus == null) {
            throw new Exception("transactionStatus 不能为空");
        }
        dataSourceTransactionManager.rollback(transactionStatus);
    }


}
