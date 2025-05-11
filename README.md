# Anime App

## Features Implemented

- Initial screen showing a list of anime.
- Detail screen for each anime item with relevant information with trailer.

## Assumptions

- No pagination is required for the anime list.
- The detail API (`https://api.jikan.moe/v4/anime/{anime_id}`) does not provide a Main Cast list, so that section is omitted.
- Portrait mode support only; landscape is not handled.

## Known Limitations

- No handling for device rotation (landscape mode).
- No main cast information in the detail view due to API limitation.

## Library Used

- [android-youtube-player](https://github.com/PierfrancescoSoffritti/android-youtube-player) for embedding YouTube videos.

