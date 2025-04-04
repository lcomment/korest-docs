import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import HomepageFeatures from '@site/src/components/HomepageFeatures';
import Heading from '@theme/Heading';
import styles from './index.module.css';

function HomepageHeader() {
    const {siteConfig} = useDocusaurusContext();

    return (
        <header className={styles.thumbnails}>
            <img
                src="https://github.com/lcomment/korest-docs/blob/master/docs/static/img/logo.png?raw=true"
                alt="Tags"
                className={clsx(styles.banner)}
            />

            <div className={clsx('container', styles.wrapperText)}>
                <Heading as="h2" className={styles.title}>
                    {siteConfig.title}
                </Heading>
                <p className={styles.subTitle}>{siteConfig.tagline}</p>
                <div className={styles.buttons}>
                    <Link
                        className="button button--secondary button--lg"
                        to="/docs/getting-started/requirements">
                        Getting Started
                    </Link>
                </div>
            </div>
        </header>
    );
}

export default function Home() {
    const {siteConfig} = useDocusaurusContext();

    return (
        <Layout
            title={`${siteConfig.title} Docs`}
            description={`${siteConfig.tagline}`}>
            <HomepageHeader/>
            <main>
                <HomepageFeatures/>
            </main>
        </Layout>
    );
}
