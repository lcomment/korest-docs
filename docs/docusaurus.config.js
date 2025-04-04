// @ts-check
// `@type` JSDoc annotations allow editor autocompletion and type checking
// (when paired with `@ts-check`).
// There are various equivalent ways to declare your Docusaurus config.
// See: https://docusaurus.io/docs/api/docusaurus-config

import {themes as prismThemes} from 'prism-react-renderer';

/** @type {import('@docusaurus/types').Config} */
const config = {
    title: 'Korest Docs',
    tagline: 'Spring REST Docs extension library using Kotlin DSL',
    favicon: 'img/favicon.ico',

    url: 'https://lcomment.github.io',
    baseUrl: '/korest-docs',

    organizationName: 'lcomment',
    projectName: 'korest-docs',
    deploymentBranch: 'gh-pages',

    onBrokenLinks: 'throw',
    onBrokenMarkdownLinks: 'warn',

    // i18n: {
    //   defaultLocale: 'en',
    //   locales: ['en', 'ko'],
    //   localeConfigs: {
    //     en: {
    //       htmlLang: 'en-GB'
    //     },
    //     ko: {
    //       direction: 'ltr'
    //     }
    //   }
    // },

    presets: [
        [
            'classic',
            /** @type {import('@docusaurus/preset-classic').Options} */
            ({
                docs: {
                    sidebarPath: './sidebars.js',
                    includeCurrentVersion: false,
                },
                theme: {
                    customCss: './src/css/custom.css',
                },
            }),
        ],
    ],
    themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
        ({
            // Replace with your project's social card
            // image: 'img/docusaurus-social-card.jpg',
            navbar: {
                title: 'Korest Docs',
                logo: {
                    alt: 'Korest Docs Logo',
                    src: 'https://github.com/lcomment/korest-docs/blob/master/docs/static/img/logo.png?raw=true',
                },
                items: [
                    {
                        type: 'docSidebar',
                        sidebarId: 'tutorialSidebar',
                        position: 'left',
                        label: 'Docs',
                    },
                    {
                        type: 'docsVersionDropdown',
                        sidebarId: 'docsVersionDropdown',
                        position: 'left',
                        label: 'Version',
                    },
                    {
                        href: 'https://github.com/lcomment/korest-docs',
                        label: 'GitHub',
                        position: 'right',
                    },
                    {
                        type: 'localeDropdown',
                        position: 'right',
                    },
                ],
            },
            footer: {
                style: 'dark',
                links: [
                    {
                        title: 'More',
                        items: [
                            {
                                label: 'GitHub',
                                href: 'https://github.com/lcomment',
                            },
                            {
                                label: 'Blog',
                                href: 'https://velog.io/@komment/Korest-Docs-2',
                            },
                        ],
                    },
                    {
                        title: 'Contact',
                        items: [
                            {
                                label: 'Email',
                                href: 'mailto:komment.dev@gmail.com',
                            },
                        ],
                    },
                ],
                copyright: `Copyright © ${new Date().getFullYear()} Hyunseok Ko. Built with Docusaurus.`,
            },
            prism: {
                theme: prismThemes.github,
                darkTheme: prismThemes.dracula,
            },
        }),
};

export default config;
