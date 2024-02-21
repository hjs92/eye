2022-02-14 10:15:22,667 [http-nio-8088-exec-7] ==>  Preparing: select * from sys_user where username = ? 
2022-02-14 10:15:22,967 [http-nio-8088-exec-7] ==> Parameters: admin(String)
2022-02-14 10:15:23,659 [http-nio-8088-exec-7] <==      Total: 1
2022-02-14 10:15:24,532 [http-nio-8088-exec-7] ==>  Preparing: select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = ? order by p.sort 
2022-02-14 10:15:24,536 [http-nio-8088-exec-7] ==> Parameters: 1(Long)
2022-02-14 10:15:25,311 [http-nio-8088-exec-7] <==      Total: 1
2022-02-14 10:15:27,688 [http-nio-8088-exec-7] ==>  Preparing: update sys_user SET allowAt = ?, errorNum = ? where id = ? 
2022-02-14 10:15:27,694 [http-nio-8088-exec-7] ==> Parameters: 2022-02-14 10:15:26.079(Timestamp), 0(Integer), 1(Long)
2022-02-14 10:15:28,938 [http-nio-8088-exec-7] <==    Updates: 1
2022-02-14 10:15:48,680 [http-nio-8088-exec-7] ==>  Preparing: insert into sys_logs(userId,module, flag, remark, createTime) values(?, ?, ?, ?, getdate()) 
2022-02-14 10:15:48,706 [http-nio-8088-exec-7] ==> Parameters: 1(Long), 登陆(String), true(Boolean), null
2022-02-14 10:15:49,000 [http-nio-8088-exec-7] <==    Updates: 1
2022-02-14 10:18:06,697 [http-nio-8088-exec-6] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id WHERE username = ? 
2022-02-14 10:18:06,702 [http-nio-8088-exec-6] ==> Parameters: admin(String)
2022-02-14 10:18:07,192 [http-nio-8088-exec-6] <==      Total: 1
2022-02-14 10:20:57,735 [http-nio-8088-exec-2] ==>  Preparing: select * from sys_user where username = ? 
2022-02-14 10:20:57,738 [http-nio-8088-exec-2] ==> Parameters: 282427162@qq.com(String)
2022-02-14 10:20:57,856 [http-nio-8088-exec-2] <==      Total: 1
2022-02-14 10:20:57,974 [http-nio-8088-exec-2] ==>  Preparing: select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = ? order by p.sort 
2022-02-14 10:20:57,976 [http-nio-8088-exec-2] ==> Parameters: 5(Long)
2022-02-14 10:20:58,130 [http-nio-8088-exec-2] <==      Total: 1
2022-02-14 10:20:58,876 [http-nio-8088-exec-2] ==>  Preparing: select * from sys_user where username = ? 
2022-02-14 10:20:58,879 [http-nio-8088-exec-2] ==> Parameters: 282427162@qq.com(String)
2022-02-14 10:20:59,017 [http-nio-8088-exec-2] <==      Total: 1
2022-02-14 10:20:59,041 [http-nio-8088-exec-2] ==>  Preparing: update sys_user SET username = ?, password = ?, status = ?, pwdexpireTime = ?, type = ?, isenabled = ?, allowAt = ?, realName = ?, organization = ?, job = ?, errorNum = ? where id = ? 
2022-02-14 10:20:59,044 [http-nio-8088-exec-2] ==> Parameters: 282427162@qq.com(String), $2a$10$4v8pxx7fMgsp2eAiz7wL8uoSHzx6EdChKSP07re4Wx7KAtKpRX/P.(String), 1(Integer), 2021-12-28 16:03:56.817(Timestamp), 1(Integer), 1(Integer), 2022-02-14 10:20:59.019(Timestamp), hhb(String), yyit(String), web(String), 1(Integer), 5(Long)
2022-02-14 10:20:59,092 [http-nio-8088-exec-2] <==    Updates: 1
2022-02-14 10:22:35,185 [http-nio-8088-exec-8] ==>  Preparing: select * from sys_user where username = ? 
2022-02-14 10:22:35,189 [http-nio-8088-exec-8] ==> Parameters: test(String)
2022-02-14 10:22:35,305 [http-nio-8088-exec-8] <==      Total: 1
2022-02-14 10:22:35,345 [http-nio-8088-exec-8] ==>  Preparing: select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = ? order by p.sort 
2022-02-14 10:22:35,347 [http-nio-8088-exec-8] ==> Parameters: 4(Long)
2022-02-14 10:22:35,365 [http-nio-8088-exec-8] <==      Total: 0
2022-02-14 10:22:35,947 [http-nio-8088-exec-8] ==>  Preparing: update sys_user SET allowAt = ?, errorNum = ? where id = ? 
2022-02-14 10:22:35,949 [http-nio-8088-exec-8] ==> Parameters: 2022-02-14 10:22:35.769(Timestamp), 0(Integer), 4(Long)
2022-02-14 10:22:35,972 [http-nio-8088-exec-8] <==    Updates: 1
2022-02-14 10:22:36,135 [http-nio-8088-exec-8] ==>  Preparing: insert into sys_logs(userId,module, flag, remark, createTime) values(?, ?, ?, ?, getdate()) 
2022-02-14 10:22:36,140 [http-nio-8088-exec-8] ==> Parameters: 4(Long), 登陆(String), true(Boolean), null
2022-02-14 10:22:36,239 [http-nio-8088-exec-8] <==    Updates: 1
2022-02-14 10:22:54,752 [http-nio-8088-exec-10] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id WHERE username = ? 
2022-02-14 10:22:54,758 [http-nio-8088-exec-10] ==> Parameters: test(String)
2022-02-14 10:22:54,861 [http-nio-8088-exec-10] <==      Total: 1
2022-02-14 10:22:55,014 [http-nio-8088-exec-10] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id WHERE username = ? order by a.id desc) and username = ? order by a.id desc 
2022-02-14 10:22:55,016 [http-nio-8088-exec-10] ==> Parameters: test(String), test(String)
2022-02-14 10:22:55,361 [http-nio-8088-exec-10] <==      Total: 4
2022-02-14 10:23:16,254 [http-nio-8088-exec-9] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 10:23:16,257 [http-nio-8088-exec-9] ==> Parameters: 
2022-02-14 10:23:16,330 [http-nio-8088-exec-9] <==      Total: 1
2022-02-14 10:23:16,422 [http-nio-8088-exec-9] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 10:23:16,424 [http-nio-8088-exec-9] ==> Parameters: 
2022-02-14 10:23:16,528 [http-nio-8088-exec-9] <==      Total: 5
2022-02-14 10:42:43,866 [http-nio-8088-exec-7] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 10:42:43,868 [http-nio-8088-exec-7] ==> Parameters: 
2022-02-14 10:42:43,979 [http-nio-8088-exec-7] <==      Total: 1
2022-02-14 10:42:44,049 [http-nio-8088-exec-7] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 10:42:44,051 [http-nio-8088-exec-7] ==> Parameters: 
2022-02-14 10:42:44,316 [http-nio-8088-exec-7] <==      Total: 5
2022-02-14 10:47:13,198 [http-nio-8088-exec-6] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id WHERE username = ? 
2022-02-14 10:47:13,202 [http-nio-8088-exec-6] ==> Parameters: test(String)
2022-02-14 10:47:13,216 [http-nio-8088-exec-6] <==      Total: 1
2022-02-14 10:47:13,297 [http-nio-8088-exec-6] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id WHERE username = ? order by a.id desc) and username = ? order by a.id desc 
2022-02-14 10:47:13,299 [http-nio-8088-exec-6] ==> Parameters: test(String), test(String)
2022-02-14 10:47:13,342 [http-nio-8088-exec-6] <==      Total: 4
2022-02-14 10:48:55,325 [http-nio-8088-exec-2] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 10:48:55,327 [http-nio-8088-exec-2] ==> Parameters: 
2022-02-14 10:48:55,481 [http-nio-8088-exec-2] <==      Total: 1
2022-02-14 10:48:55,531 [http-nio-8088-exec-2] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 10:48:55,587 [http-nio-8088-exec-2] ==> Parameters: 
2022-02-14 10:48:55,863 [http-nio-8088-exec-2] <==      Total: 5
2022-02-14 11:47:43,542 [http-nio-8088-exec-1] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 11:47:44,271 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 11:47:44,579 [http-nio-8088-exec-1] <==      Total: 1
2022-02-14 11:47:44,738 [http-nio-8088-exec-1] ==>  Preparing: select top 5 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 5) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 11:47:44,742 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 11:47:45,053 [http-nio-8088-exec-1] <==      Total: 5
2022-02-14 14:12:03,004 [http-nio-8088-exec-8] ==>  Preparing: select * from sys_user where username = ? 
2022-02-14 14:12:03,031 [http-nio-8088-exec-8] ==> Parameters: security(String)
2022-02-14 14:12:03,104 [http-nio-8088-exec-8] <==      Total: 1
2022-02-14 14:12:03,812 [http-nio-8088-exec-8] ==>  Preparing: select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = ? order by p.sort 
2022-02-14 14:12:03,816 [http-nio-8088-exec-8] ==> Parameters: 2(Long)
2022-02-14 14:12:03,871 [http-nio-8088-exec-8] <==      Total: 2
2022-02-14 14:12:04,132 [http-nio-8088-exec-8] ==>  Preparing: update sys_user SET allowAt = ?, errorNum = ? where id = ? 
2022-02-14 14:12:04,138 [http-nio-8088-exec-8] ==> Parameters: 2022-02-14 14:12:04.089(Timestamp), 0(Integer), 2(Long)
2022-02-14 14:12:04,168 [http-nio-8088-exec-8] <==    Updates: 1
2022-02-14 14:12:04,326 [http-nio-8088-exec-8] ==>  Preparing: insert into sys_logs(userId,module, flag, remark, createTime) values(?, ?, ?, ?, getdate()) 
2022-02-14 14:12:04,348 [http-nio-8088-exec-8] ==> Parameters: 2(Long), 登陆(String), true(Boolean), null
2022-02-14 14:12:04,682 [http-nio-8088-exec-8] <==    Updates: 1
2022-02-14 14:12:07,122 [http-nio-8088-exec-1] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:12:07,127 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 14:12:07,311 [http-nio-8088-exec-1] <==      Total: 1
2022-02-14 14:12:07,490 [http-nio-8088-exec-1] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:12:07,492 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 14:12:07,832 [http-nio-8088-exec-1] <==      Total: 10
2022-02-14 14:13:40,495 [http-nio-8088-exec-3] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:13:40,497 [http-nio-8088-exec-3] ==> Parameters: 
2022-02-14 14:13:40,559 [http-nio-8088-exec-3] <==      Total: 1
2022-02-14 14:13:40,572 [http-nio-8088-exec-3] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:13:40,574 [http-nio-8088-exec-3] ==> Parameters: 
2022-02-14 14:13:40,606 [http-nio-8088-exec-3] <==      Total: 10
2022-02-14 14:14:34,557 [http-nio-8088-exec-5] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:14:34,559 [http-nio-8088-exec-5] ==> Parameters: 
2022-02-14 14:14:34,569 [http-nio-8088-exec-5] <==      Total: 1
2022-02-14 14:14:34,581 [http-nio-8088-exec-5] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:14:34,583 [http-nio-8088-exec-5] ==> Parameters: 
2022-02-14 14:14:34,643 [http-nio-8088-exec-5] <==      Total: 10
2022-02-14 14:15:29,666 [http-nio-8088-exec-6] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:15:29,668 [http-nio-8088-exec-6] ==> Parameters: 
2022-02-14 14:15:29,674 [http-nio-8088-exec-6] <==      Total: 1
2022-02-14 14:15:29,685 [http-nio-8088-exec-6] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:15:29,688 [http-nio-8088-exec-6] ==> Parameters: 
2022-02-14 14:15:29,721 [http-nio-8088-exec-6] <==      Total: 10
2022-02-14 14:16:06,096 [http-nio-8088-exec-7] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:16:06,098 [http-nio-8088-exec-7] ==> Parameters: 
2022-02-14 14:16:06,104 [http-nio-8088-exec-7] <==      Total: 1
2022-02-14 14:16:06,111 [http-nio-8088-exec-7] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:16:06,113 [http-nio-8088-exec-7] ==> Parameters: 
2022-02-14 14:16:06,133 [http-nio-8088-exec-7] <==      Total: 10
2022-02-14 14:16:44,628 [http-nio-8088-exec-8] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:16:44,630 [http-nio-8088-exec-8] ==> Parameters: 
2022-02-14 14:16:44,686 [http-nio-8088-exec-8] <==      Total: 1
2022-02-14 14:16:44,695 [http-nio-8088-exec-8] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:16:44,697 [http-nio-8088-exec-8] ==> Parameters: 
2022-02-14 14:16:44,725 [http-nio-8088-exec-8] <==      Total: 10
2022-02-14 14:17:59,826 [http-nio-8088-exec-10] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:17:59,829 [http-nio-8088-exec-10] ==> Parameters: 
2022-02-14 14:17:59,867 [http-nio-8088-exec-10] <==      Total: 1
2022-02-14 14:17:59,941 [http-nio-8088-exec-10] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:17:59,942 [http-nio-8088-exec-10] ==> Parameters: 
2022-02-14 14:18:00,014 [http-nio-8088-exec-10] <==      Total: 10
2022-02-14 14:18:03,983 [http-nio-8088-exec-1] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:18:03,985 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 14:18:03,991 [http-nio-8088-exec-1] <==      Total: 1
2022-02-14 14:18:04,004 [http-nio-8088-exec-1] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:18:04,051 [http-nio-8088-exec-1] ==> Parameters: 
2022-02-14 14:18:04,070 [http-nio-8088-exec-1] <==      Total: 10
2022-02-14 14:18:24,155 [http-nio-8088-exec-2] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:18:24,157 [http-nio-8088-exec-2] ==> Parameters: 
2022-02-14 14:18:24,170 [http-nio-8088-exec-2] <==      Total: 1
2022-02-14 14:18:24,189 [http-nio-8088-exec-2] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:18:24,198 [http-nio-8088-exec-2] ==> Parameters: 
2022-02-14 14:18:24,275 [http-nio-8088-exec-2] <==      Total: 10
2022-02-14 14:18:32,676 [http-nio-8088-exec-4] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:18:32,688 [http-nio-8088-exec-4] ==> Parameters: 
2022-02-14 14:18:32,734 [http-nio-8088-exec-4] <==      Total: 1
2022-02-14 14:18:32,746 [http-nio-8088-exec-4] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (1 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:18:32,749 [http-nio-8088-exec-4] ==> Parameters: 
2022-02-14 14:18:32,966 [http-nio-8088-exec-4] <==      Total: 7
2022-02-14 14:18:35,470 [http-nio-8088-exec-3] ==>  Preparing: select count(1) from uploadRecord a inner join sys_user b on a.userId = b.id 
2022-02-14 14:18:35,472 [http-nio-8088-exec-3] ==> Parameters: 
2022-02-14 14:18:35,493 [http-nio-8088-exec-3] <==      Total: 1
2022-02-14 14:18:35,503 [http-nio-8088-exec-3] ==>  Preparing: select top 10 a.id, a.dateTime, a.state,a.downloadLink1, a.downloadLink2,b.username,b.realName,b.organization,a.type from uploadRecord a inner join sys_user b on a.userId = b.id where a.id not in (select top (0 * 10) a.id from uploadRecord a inner join sys_user b on a.userId = b.id order by a.id desc) order by a.id desc 
2022-02-14 14:18:35,504 [http-nio-8088-exec-3] ==> Parameters: 
2022-02-14 14:18:35,518 [http-nio-8088-exec-3] <==      Total: 10
