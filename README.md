# TestApplication

Below is the application as required. The app has 2 views:

1) 1st view is the storyAuthor View , which contains the main author.

2) 2nd view is the AuthorDetail view, which is visible by clicking an author.

We can follow any view from authordetail and all the views will be followed, along with the Author card or vice-versa. This is achieved
using Custom Listeners instead of NotifyDataSetChanged() and instead of that, notifyItemRangeChanged() is used so that UI/UX remains perfect.

Android concepts used are: RecyclerViews, CustomListeners, Activities and CustomAdapters.
Image Loading is done using Glide library.
