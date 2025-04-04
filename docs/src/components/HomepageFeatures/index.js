import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

const FeatureList = [
    {
        title: 'Define API documentation using Kotlin DSL',
        //Svg: require('@site/static/img/undraw_docusaurus_mountain.svg').default,
        description: (
            <>
                Korest Docs provides a declarative approach to API documentation by leveraging Kotlin DSL. It allows for
                concise, readable, and structured API specifications without the verbosity of traditional approaches.
            </>
        ),
    },
    {
        title: 'Intelligent type inference and customizable snippets',
        //Svg: require('@site/static/img/undraw_docusaurus_tree.svg').default,
        description: (
            <>
                Korest Docs automatically infers types from request and response models, reducing redundant manual
                definitions. Additionally, it allows restructuring default snippets for greater flexibility.
            </>
        ),
    },
    {
        title: 'Coming soon',
        //Svg: require('@site/static/img/undraw_docusaurus_react.svg').default,
        description: (
            <>
                Korest Docs supports additional features such as automatic conversion to Swagger UI for better
                visualization, along with various extensions to enhance API documentation workflows. Furthermore, we are
                continuously developing additional convenience features to improve usability.
            </>
        ),
    },
];

function Feature({Svg, title, description}) {
    return (
        <div className={clsx('col col--4')}>
            {/*<div className="text--center">*/}
            {/*  <Svg className={styles.featureSvg} role="img" />*/}
            {/*</div>*/}
            <div className="text--center padding-horiz--md margin-top--lg">
                <Heading as="h3">{title}</Heading>
                <p>{description}</p>
            </div>
        </div>
    );
}

export default function HomepageFeatures() {
    return (
        <section className={styles.features}>
            <div className="container">
                <div className="row">
                    {FeatureList.map((props, idx) => (
                        <Feature key={idx} {...props} />
                    ))}
                </div>
            </div>
        </section>
    );
}
