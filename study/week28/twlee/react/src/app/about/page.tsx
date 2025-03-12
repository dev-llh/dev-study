import styles from './styles.module.css'
import Image from 'next/image'

const user = {
  name: 'lee',
  imageUrl: '/sample.png',
  imageSize: 240,
}

export default function About() {
  return (
    <div className={styles.about}>
      <h1>{user.name}</h1>
      <Image
        className={styles.avatar}
        src={user.imageUrl}
        alt="hello"
        width={user.imageSize}
        height={user.imageSize}
      />
    </div>
  )
}