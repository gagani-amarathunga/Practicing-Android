package com.android.acc.mynotes;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.android.acc.mynotes.ui.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkArgument;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/* Tests for MainActivity, the screen with all the notes in a grid */
@RunWith(AndroidJUnit4.class)
public class NotesScreenTest {

    /* ActivityTestRule is a JUnit to launch the MainAcitivity under test.*/
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    /* A custom matcher which matches the item in the recycler view by its text
     *
     * @param noteContent note content the text to match
     * @return Matcher that matches text in the given view */
    private Matcher<View> withItemText(final String noteContent) {
        checkArgument(!TextUtils.isEmpty(noteContent), "Note content cannot be null or empty");
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View note) {
                return allOf(
                        isDescendantOfA(isAssignableFrom(RecyclerView.class)),
                        withText(noteContent)).matches(note);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is isDescendantOfA recycler view with text " + noteContent);
            }
        };
    }


    /* Open the AddNoteActivity at floating action button click */
    @Test
    public void clickAddNoteFac_openAddNoteUi() {

        // Click floating action button to add a note
        onView(withId(R.id.addNoteFab)).perform(click());

        // Check if add note screen is displayed
        onView(withId(R.id.noteContentEditText)).check(matches(isDisplayed()));
    }

    /* Adding a note by clicking the floating action button using Espresso */
    @Test
    public void addNoteToNoteGrid() {

        final String noteContent = "UI testing of adding note to the list using Espresso";

        // Click on the add note button
        onView(withId(R.id.addNoteFab)).perform(click());

        // Add note content
        onView(withId(R.id.noteContentEditText)).perform(typeText(noteContent), closeSoftKeyboard());

        // Save the note
        onView(withId(R.id.addNoteButton)).perform(click());

        // pressing back button
        onView(isRoot()).perform(ViewActions.pressBack());

        // Scroll notes list to added note, by finding its content
        onView(withId(R.id.recyclerViewNotes)).perform(
                scrollTo(hasDescendant(withText(noteContent))));

        // Verify note is displayed on screen
        onView(withItemText(noteContent)).check(matches(isDisplayed()));
    }

}