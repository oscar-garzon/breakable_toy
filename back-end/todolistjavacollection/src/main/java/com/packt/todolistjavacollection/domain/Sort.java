package com.packt.todolistjavacollection.domain;

// The object of this class is to provide information for the possible
// sortings in a ToDo list: priority and/or due date. 
// Only one order could be required in which case only the 
// principalSorting is active (not null).
// If two orders (stable sort) are required then principalSortBy has the
// main order and secondarySortBy has the second order.
public class Sort {

    private String principalSortBy;
    private String principalSortOrder;
    private String secondarySortBy;
    private String secondarySortOrder;

    public Sort(String principalSortBy, 
                String principalSortOrder,
                String secondarySortBy,
                String secondarySortOrder) {
        this.principalSortBy = principalSortBy;
        this.principalSortOrder = principalSortOrder;
        this.secondarySortBy = secondarySortBy;
        this.secondarySortOrder = secondarySortOrder;
    }

    public String getPrincipalSortBy() {
        return principalSortBy;
    }

    public String getPrincipalSortOrder() {
        return principalSortOrder;
    }

    public String getSecondarySortBy() {
        return secondarySortBy;
    }

    public String getSecondarySortOrder() {
        return secondarySortOrder;
    }

    public boolean hasPrincipalSort(){
        return !principalSortBy.equals("");
    }

    public boolean hasSecondarySort(){
        return !secondarySortBy.equals("");
    }

    @Override
    public String toString(){
        return "principalSortBy=" + principalSortBy + " principalSortOrder=" + principalSortOrder 
               + " secondarySortBy=" + secondarySortBy + " secondarySortOrder=" + secondarySortOrder;
    }



    

}
