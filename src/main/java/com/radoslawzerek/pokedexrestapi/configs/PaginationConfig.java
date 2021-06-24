package com.radoslawzerek.pokedexrestapi.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
public class PaginationConfig {

    private boolean hasNextPage;
    private boolean hasPrevPage;

    private int requestedPageSize; // max cartItems per page

    private int currentPageNumber;
    private int numberOfPages;

    private int currentPokemonsCount; // cartItems in this page
    private long totalPokemonsCount; // total cartItems in total
    private long offset;

    private int nextPageNumber;
    private int prevPageNumber;

    private String nextPageUrl;
    private String prevPageUrl;

    public static PaginationConfig build(Page resourcePage, String basePath) {
        PaginationConfig paginationConfig = new PaginationConfig();
        Pageable pageable = resourcePage.getPageable();

        paginationConfig.setTotalPokemonsCount(resourcePage.getTotalElements());
        paginationConfig.setOffset(pageable.getOffset());
        paginationConfig.setRequestedPageSize(pageable.getPageSize());
        paginationConfig.setCurrentPokemonsCount(resourcePage.getContent().size());
        paginationConfig.setNumberOfPages(resourcePage.getTotalPages());

        paginationConfig.setCurrentPageNumber(resourcePage.getNumber() + 1);

        paginationConfig.setHasNextPage(resourcePage.hasNext());
        paginationConfig.setHasPrevPage(resourcePage.hasPrevious());

        if (resourcePage.hasNext()) {
            paginationConfig.setNextPageNumber(resourcePage.getPageable().next().getPageNumber() + 1);
            paginationConfig.setNextPageUrl(String.format("%s?page_size=%d&page=%d",
                    basePath, paginationConfig.getRequestedPageSize(), paginationConfig.getNextPageNumber()));
        } else {
            paginationConfig.setNextPageNumber(paginationConfig.getNumberOfPages());
            paginationConfig.setNextPageUrl(String.format("%s?page_size=%d&page=%d",
                    basePath, paginationConfig.getRequestedPageSize(), paginationConfig.getNextPageNumber()));
        }
        if (resourcePage.hasPrevious()) {
            paginationConfig.setPrevPageNumber(resourcePage.getPageable().previousOrFirst().getPageNumber() + 1);

            paginationConfig.setPrevPageUrl(String.format("%s?page_size=%d&page=%d",
                    basePath, paginationConfig.getRequestedPageSize(),
                    paginationConfig.getPrevPageNumber()));
        } else {
            paginationConfig.setPrevPageNumber(1);
            paginationConfig.setPrevPageUrl(String.format("%s?page_size=%d&page=%d",
                    basePath, paginationConfig.getRequestedPageSize(), paginationConfig.getPrevPageNumber()));
        }
        return paginationConfig;
    }
}
