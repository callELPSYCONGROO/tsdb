# tsdb
> InfluxDB数据库Java客户端

![InfluxDB-Java](https://github.com/callELPSYCONGROO/tsdb/blob/master/refer/InfluxDB%E6%8B%93%E6%89%91%E5%9B%BE.png?raw=true)

### 代理网关influxdb-proxy

使用influxdb-java客户端连接InfluxDB数据库。

### 数据处理data-handling

连接kafka服务器从其中获取数据，处理数据之后调用代理网关influxdb-proxy将数据插入数据库。

### 查询代理query

预处理查询语句，过滤危险查询代码，调用查询InfluxDB数据库。

### 公用模块common

变量、常量、异常、接口和工具类。

### 注册中心ra

代理网关influxdb-proxy中的接口由其他模块公用，使用SpringCloud微服务方式提供接口，可以将项目拓展为集群式高可用的服务。

