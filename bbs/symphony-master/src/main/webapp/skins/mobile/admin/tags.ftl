<#include "macro-admin.ftl">
<#include "../macro-pagination.ftl">
<@admin "tags">
<div class="list content admin">
    <div class="fn-hr10"></div>
    <form method="GET" action="tags" class="form wrapper">
        <input name="title" type="text" placeholder="${tagLabel}"/>
        <button type="submit" class="green">${searchLabel}</button>
    </form>
    <ul>
        <#list tags as item>
        <li>
            <div class="fn-clear first">
                <a href="/tag/${item.tagTitle?url('utf-8')}">${item.tagTitle}</a> &nbsp;
                <#if item.tagStatus == 0>
                <span class="ft-gray">${validLabel}</span>
                <#else>
                <font class="ft-red">${banLabel}</font>
                </#if>
                <a href="/admin/tag/${item.oId}" class="fn-right icon-edit" title="${editLabel}"></a>
            </div>
            <div>
                <#if item.tagIconPath != ''>
                <div class="avatar" style="background-image:url('${staticServePath}/images/tags/${item.tagIconPath}')"></div>
                </#if>
                ${item.tagDescription}
                <div class="ft-gray fn-clear">
                    <span class="icon-view fn-right" title="${refCountLabel}">${item.tagReferenceCount}</span>
                    <span class="icon-cmts fn-right" title="${commentCountLabel}"> ${item.tagCommentCount} &nbsp;</span>
                    <span class="icon-date" title="${createTimeLabel}"></span>
                    ${item.tagCreateTime?string('yyyy-MM-dd HH:mm')}
                </div>
            </div>
        </li>
        </#list>
    </ul>
    <@pagination url="/admin/tags"/>
</div>
</@admin>
