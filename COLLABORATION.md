# Two-person GitHub contribution plan

Keep all Java files in `src/com/escapethelostworld/` so they remain in one Java package.

## Contributor 1 — Game / UI
Own and commit these files:
- `Main.java` — application entry point
- `GameFrame.java` — main Swing window
- `GamePanel.java` — screens, rendering, controls, gameplay loop and UI behavior
- `Screen.java` — screen states
- `WorldType.java` — world definitions
- `Obstacle.java`, `Coin.java`, `Power.java` — gameplay objects

Suggested GitHub branch: `contributor-1-game-ui`

## Contributor 2 — Game data / support systems
Own and commit these files:
- `CharacterData.java`, `CharacterCatalog.java` — character information/catalog
- `SaveManager.java` — local save/settings data
- `GameDatabase.java` — run-history persistence (MySQL when configured, CSV fallback)
- `SoundManager.java` — music and sound effects
- `assets/` — audio resources
- `EscapeTheLostWorld_MySQL.sql`, `EscapeTheLostWorldDB.properties.example` — database setup/config example
- `README.md` documentation improvements

Suggested GitHub branch: `contributor-2-data-audio`

## Safe collaboration workflow
1. Contributor 1 creates the GitHub repository and pushes the complete starter project.
2. Contributor 1 invites Contributor 2 under GitHub repository Settings → Collaborators.
3. Each person clones the same repository to their own computer.
4. Each creates their own branch and edits only the assigned files.
5. Commit and push the branch, then open a Pull Request to `main`.
6. Review and merge both pull requests. Compile and run the merged project.

## Commands
```bash
git clone <REPOSITORY-URL>
cd Escape_The_Lost_World_V2_GitHub_Team_Project
git checkout -b contributor-1-game-ui
# after editing assigned files:
git add src/com/escapethelostworld/Main.java src/com/escapethelostworld/GameFrame.java src/com/escapethelostworld/GamePanel.java src/com/escapethelostworld/Screen.java src/com/escapethelostworld/WorldType.java src/com/escapethelostworld/Obstacle.java src/com/escapethelostworld/Coin.java src/com/escapethelostworld/Power.java
git commit -m "Add game UI and gameplay module"
git push -u origin contributor-1-game-ui
```
Contributor 2 should use `git checkout -b contributor-2-data-audio` and stage only their assigned files.

**Important:** These assignments are a practical division of the existing code, not a claim that both contributors independently wrote the whole project. Each person should explain the files they actually modified and committed. Avoid editing the same file at the same time; `GamePanel.java` is the main shared integration point, so coordinate changes to it.
