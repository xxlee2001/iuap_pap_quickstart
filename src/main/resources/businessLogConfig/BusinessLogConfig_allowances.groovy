class BusinessLogConfig_allowances {

    def context;
    
    def allowances_insertSelective() {
        "A员工津贴：执行保存方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time},编码为${context._methodReturn.code},名称为${context._param0.name}"
    }  
    
    def allowances_updateSelective() {
        "A员工津贴：执行修改方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time},编码为${context._methodReturn.code},名称为${context._param0.name}"
    }
    
    def allowances_saveMultiple() {
        "A员工津贴：执行批量添加方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time}"
    }
    
    def allowances_updateMultiple() {
        "A员工津贴：执行批量修改方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time}"
    }
    
    def allowances_deleteBatch() {
        "A员工津贴：执行删除方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time}"
    }
}
