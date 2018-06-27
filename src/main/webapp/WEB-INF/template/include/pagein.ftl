<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}" />
<input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}" />
<input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}" />
<input name="departmentType" class="departmentType" value="" type="hidden">
[#--[#if totalPages > 1]--]
<div class="pagination">
[#if page.isFirstPage]
    <span class="firstPage">&nbsp;</span>
[#else]
    <a class="firstPage" href="javascript: $.pageSkip(${page.firstPage});">&nbsp;</a>
[/#if]
[#if page.hasPreviousPage]
    <a class="previousPage" href="javascript: $.pageSkip(${page.prePage});">&nbsp;</a>
[#else]
    <span class="previousPage">&nbsp;</span>
[/#if]
[#list page.navigatepageNums as segmentPageNumber]
    [#if segmentPageNumber_index == 0 && segmentPageNumber > page.firstPage + 1]
        <span class="pageBreak">...</span>
    [/#if]
    [#if segmentPageNumber != page.pageNum]
        <a href="javascript: $.pageSkip(${segmentPageNumber});">${segmentPageNumber}</a>
    [#else]
        <span class="currentPage">${segmentPageNumber}</span>
    [/#if]
    [#if !segmentPageNumber_has_next && segmentPageNumber <  page.lastPage - 1]
        <span class="pageBreak">...</span>
    [/#if]
[/#list]
[#if page.hasNextPage]
    <a class="nextPage" href="javascript: $.pageSkip(${page.nextPage});">&nbsp;</a>
[#else]
    <span class="nextPage">&nbsp;</span>
[/#if]
[#if page.isLastPage]
    <span class="lastPage">&nbsp;</span>
[#else]
    <a class="lastPage" href="javascript: $.pageSkip(${page.lastPage});">&nbsp;</a>
[/#if]
    <span class="pageSkip">
			${message("page.totalPages", page.pages)} ${message("page.pageNumber", '<input id="currpage" name="currpage" value="' + page.pageNum + '" maxlength="9" size="6" onpaste="return false;" />')}<button type="submit">&nbsp;</button>
		</span>
</div>
[#--[/#if]--]