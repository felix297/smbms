<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面 >> 用户修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath}\user\">
            <input type="hidden" name="id" value="${user.id}"/>
            <div>
                <label for="userName">用户名称：</label>
                <input type="text" name="userName" id="userName" value="${user.userName}">
                <font color="red"></font>
            </div>
            <div>
                <label >用户性别：</label>
                <select name="gender" id="gender">
                    <c:choose>
                        <c:when test="${user.gender =='man'}">
                            <option value="man" selected="selected">男</option>
                            <option value="woman">女</option>
                        </c:when>
                        <c:otherwise>
                            <option value="man">男</option>
                            <option value="woman" selected="selected">女</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div>
                <label for="birthday">出生日期：</label>
                <input type="text" Class="Wdate" id="birthday" name="birthday" value="${user.birthday}"
                       readonly="readonly" onclick="WdatePicker({el:this});">
                <font color="red"></font>
            </div>

            <div>
                <label for="phone">用户电话：</label>
                <input type="text" name="phone" id="phone" value="${user.phone}">
                <font color="red"></font>
            </div>
            <div>
                <label for="address">用户地址：</label>
                <input type="text" name="address" id="address" value="${user.address}">
            </div>
            <div>
                <label >用户角色：</label>
                <!-- 列出所有的角色分类 -->
                <!-- <select name="userRole" id="userRole"></select> -->
                <select name="userRole" id="userRole">
                    <c:if test="${roleList != null}">
                        <option value="0">--请选择--</option>
                        <c:forEach var="role" items="${roleList}">
                            <option <c:if test="${role.id == user.userRole}">
                                selected="selected"
                            </c:if>
                                    value="${role.id}">

                                    ${role.roleName}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
                <font color="red"></font>
            </div>
<%--            <div>--%>
<%--                <label >用户角色：</label>--%>
<%--                <select name="queryUserRole">--%>
<%--                    <c:if test="${roleList != null}">--%>
<%--                        <option value="0">--请选择--</option>--%>
<%--                        <c:forEach var="role" items="${roleList}">--%>
<%--                            <option <c:if test="${role.id == queryUserRole}">--%>
<%--                                selected="selected"--%>
<%--                            </c:if>--%>
<%--                                    value="${role.id}">--%>

<%--                                    ${role.roleName}--%>
<%--                            </option>--%>
<%--                        </c:forEach>--%>
<%--                    </c:if>--%>
<%--                </select>--%>
<%--                <font color="red"></font>--%>
<%--            </div>--%>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存" />
                <input type="button" id="back" name="back" value="返回"/>
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/usermodify.js"></script>
