import Image from "next/image";
import moment from "moment";
import Link from "next/link";

export default function PostPreviewCard({
  author,
  createdAt,
  imgUrl,
  id,
  title,
}: Post) {
  return (
    <Link
      href={`/posts/${id}`}
      className='transition-all duration-200 ease-in-out hover:opacity-80'
    >
      <div className='flex h-[150px] overflow-hidden rounded-3xl bg-white shadow-lg sm:h-[200px]'>
        <div className='relative flex-[1]'>
          <Image
            alt={title}
            src={imgUrl}
            fill
            className='object-cover'
            sizes='(max-width: 768px) 50vw, 20vw'
            priority
          />
        </div>
        <div className='flex flex-[1] flex-col items-center justify-center gap-5 p-2'>
          <h2 className='my-auto px-9 font-bold text-neutral sm:text-xl'>
            {title}
          </h2>
          <div className='flex items-center gap-2 text-sm text-gray-400'>
            <div>{author}</div>
            <div>{moment(createdAt).format("YYYY-MM-DD")}</div>
          </div>
        </div>
      </div>
    </Link>
  );
}
