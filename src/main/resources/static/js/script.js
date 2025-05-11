document.addEventListener("DOMContentLoaded", () => {
    const themeButton = document.getElementById("theme_change_button");
    const htmlElement = document.documentElement;
    const themeText = themeButton.querySelector("span");
    console.log("Theme from localStorage:", localStorage.getItem("theme"));
    console.log("HTML Classes:", document.documentElement.classList);

    function initializeTheme() {
        const savedTheme = localStorage.getItem("theme");
        if (savedTheme) {
            applyTheme(savedTheme);
        } else if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
            applyTheme("dark");
        } else {
            applyTheme("light");
        }
    }

    function applyTheme(theme) {
        const body = document.body;
        if (theme === "dark") {
            document.documentElement.classList.add("dark");
            body.classList.remove("text-gray-900");
            body.classList.add("text-gray-100");
            localStorage.setItem("theme", "dark");
            themeText.textContent = "Light";
        } else {
            document.documentElement.classList.remove("dark");
            body.classList.remove("text-gray-100");
            body.classList.add("text-gray-900");
            localStorage.setItem("theme", "light");
            themeText.textContent = "Dark";
        }
    }


    themeButton.addEventListener("click", () => {
        const currentTheme = htmlElement.classList.contains("dark") ? "light" : "dark";
        applyTheme(currentTheme);
    });

    
    initializeTheme();
});
