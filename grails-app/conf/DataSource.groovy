dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
    dbCreate = "update"
    properties {
        maxActive = -1
        minEvictableIdleTimeMillis=1800000
        timeBetweenEvictionRunsMillis=1800000
        numTestsPerEvictionRun=3
        testOnBorrow=true
        testWhileIdle=true
        testOnReturn=true
        validationQuery="SELECT 1"
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            url = "jdbc:mysql://localhost:3306/sp?zeroDateTimeBehavior=convertToNull"
        }
    }
    test {
        dataSource {
            dbCreate = "createDrop"
            url = "jdbc:mysql://localhost:3306/sp_test?zeroDateTimeBehavior=convertToNull"
        }
    }
    production {
        dataSource {
            url = "jdbc:mysql://localhost:3306/sp?zeroDateTimeBehavior=convertToNull"
        }
    }
}
