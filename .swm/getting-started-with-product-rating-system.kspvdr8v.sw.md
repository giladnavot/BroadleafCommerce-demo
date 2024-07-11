---
title: Getting started with Product Rating System
---
Rating in BroadleafCommerce-demo refers to the functionality of evaluating products. It is represented by the interface `RatingSummary` which provides methods to manage and retrieve rating details.

The `RatingSummary` interface is used throughout the codebase to create, read, update, and delete rating summaries. It is also used to calculate the average rating of a product and reset it when necessary.

The `RatingSummary` interface is implemented by the `RatingSummaryImpl` class. This class is used to create instances of `RatingSummary` which are then used to store and manage rating data.

The `RatingSummary` interface provides methods to get and set the ID, rating type, item ID, number of ratings, number of reviews, average rating, reviews, and ratings. These methods are used to interact with the rating data.

The `RatingSummary` interface is used in various services and DAOs (Data Access Objects) to perform operations related to ratings. For example, the `RatingService` uses it to save, delete, and read rating summaries.

The `RatingSummary` interface is also used in the `RatingDetail` and `ReviewDetail` classes to associate ratings and reviews with a rating summary. This allows for a comprehensive view of a product's ratings and reviews.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/rating/domain/RatingSummary.java" line="28">

---

# RatingSummary Interface

The `RatingSummary` interface provides methods to get and set the ID, rating type, item ID, number of ratings, number of reviews, average rating, reviews, and ratings. These methods are used to interact with the rating data.

```java
    public void setId(Long id);
    
    public RatingType getRatingType();
    
    public void setRatingType(RatingType ratingType);
    
    public String getItemId();
    
    public void setItemId(String itemId);
    
    public Integer getNumberOfRatings();
    
    public Integer getNumberOfReviews();
    
    public Double getAverageRating();
    
    public void resetAverageRating();

    public List<ReviewDetail> getReviews();
    
    public void setReviews(List<ReviewDetail> reviews);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/rating/service/RatingService.java" line="31">

---

# RatingService

The `RatingService` uses the `RatingSummary` interface to save, delete, and read rating summaries. This service is responsible for performing operations related to ratings.

```java
    public RatingSummary saveRatingSummary(RatingSummary rating);
    public void deleteRatingSummary(RatingSummary rating);
    public RatingSummary readRatingSummary(String itemId, RatingType type);
    public Map<String, RatingSummary> readRatingSummaries(List<String> itemIds, RatingType type);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/rating/dao/RatingSummaryDaoImpl.java" line="57">

---

# RatingSummaryDaoImpl

The `RatingSummaryDaoImpl` uses the `RatingSummary` interface to create a new rating summary. This DAO (Data Access Object) is responsible for interacting with the database to manage rating summaries.

```java
        summary.setItemId(itemId);
        summary.setRatingType(type);
        return summary;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/rating/domain/RatingDetail.java" line="30">

---

# RatingDetail and ReviewDetail

The `RatingDetail` and `ReviewDetail` classes use the `RatingSummary` interface to associate ratings and reviews with a rating summary. This allows for a comprehensive view of a product's ratings and reviews.

```java
    public Double getRating();
    
    public void setRating(Double newRating);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
